package com.study8.mini.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.enumf.AccountStatusEnum;
import com.study8.mini.auth.enumf.AccountStepEnum;
import com.study8.mini.auth.enumf.RoleEnum;
import com.study8.mini.auth.repository.AuthAccountRepository;
import com.study8.mini.auth.service.AuthAccountRoleService;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.auth.service.AuthRoleService;
import com.study8.mini.auth.validator.AuthAccountValidator;
import com.study8.mini.camunda.constant.ProcessConstant;
import com.study8.mini.camunda.service.CamundaService;
import com.study8.mini.common.constant.CommonDateTimeConstant;
import com.study8.mini.common.enumf.CommonLanguageEnum;
import com.study8.mini.configuration.security.UserPrincipal;
import com.study8.mini.core.constant.CoreException;
import com.study8.mini.core.constant.CoreSystem;
import com.study8.mini.core.exception.ApplicationException;
import com.study8.mini.core.util.DateTimeUtils;
import com.study8.mini.core.util.ExceptionUtils;
import com.study8.mini.core.util.ResourceUtils;
import com.study8.mini.core.util.UUIDUtils;
import com.study8.mini.pm.service.PmProcessService;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.dto.SendEmailDto;
import com.study8.mini.sys.dto.SendEmailResultDto;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.enumf.EmailTemplateEnum;
import com.study8.mini.sys.enumf.OtpTypeEnum;
import com.study8.mini.sys.service.EmailService;
import com.study8.mini.sys.service.SysOtpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * AuthAccountServiceImpl
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountServiceImpl
 */
@Service
@Transactional
@Slf4j
public class AuthAccountServiceImpl implements AuthAccountService {
    @Autowired
    private AuthAccountRepository authAccountRepository;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CamundaService camundaService;

    @Autowired
    private PmProcessService pmProcessService;

    @Autowired
    private AuthAccountValidator authAccountValidator;

    @Autowired
    private SysOtpService sysOtpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthAccountRoleService authAccountRoleService;

    @Override
    public UserPrincipal loadUserPrincipal(String username) {
        Optional<AuthAccount> account = authAccountRepository.findByUsername(username);
        if (account.isPresent()) {
            AuthAccount accountEntity = account.get();
            AuthAccountDto accountDto = objectMapper.convertValue(accountEntity, AuthAccountDto.class);

            //Roles
            List<AuthRoleDto> roles = authRoleService.getRoles(accountEntity.getId());
            accountDto.setRoles(roles);

            return UserPrincipal.build(accountDto);
        }
        return null;
    }

    @Override
    public AuthAccountDto register(AuthAccountDto dto, Locale locale)
            throws ApplicationException {
        AuthAccountDto result = new AuthAccountDto();
        AuthAccount newEntity;
        LocalDateTime today = LocalDateTime.now();

        AccountStepEnum step = AccountStepEnum.resolveByValue(dto.getStep());
        switch (step) {
            case CREATE -> {
                boolean isValidated = authAccountValidator.validateBeforeRegister(dto, locale);
                if (isValidated) {
                    AuthAccountDto data = this.getByEmail(dto.getEmail());

                    if (ObjectUtils.isEmpty(data)) {
                        AuthAccount entity = new AuthAccount();
                        entity.setEmail(dto.getEmail());
                        entity.setCode(UUIDUtils.randomUUID());
                        entity.setStatus(AccountStatusEnum.NOT_VERIFIED.getValue());
                        entity.setCreatedDate(today);
                        entity.setCreatedId(CoreSystem.SYSTEM_ID);

                        //Do create account
                        newEntity = authAccountRepository.save(entity);

                        //Do save role
                        authAccountRoleService.saveRole(newEntity.getId(), RoleEnum.ROLE_VISITOR);

                        result = objectMapper.convertValue(newEntity, AuthAccountDto.class);
                    } else {
                        result = data;
                    }
                }
            }
            case OTP -> {
                boolean isValidated = authAccountValidator.validateBeforeSendOTP(dto, locale);
                if (isValidated) {
                    //Do generate OTP
                    SysOtpDto otp = sysOtpService.generateOTP(OtpTypeEnum.VERIFY_ACCOUNT, dto.getId());

                    if (ObjectUtils.isNotEmpty(otp)) {
                        SendEmailDto sendEmailDto = new SendEmailDto();

                        sendEmailDto.setTo(Collections.singletonList(dto.getEmail()));
                        sendEmailDto.setTemplateCode(EmailTemplateEnum.VERIFY_EMAIL);
                        sendEmailDto.setSubject(ResourceUtils.getMessage(SysConstant.EMAIL_001_SUBJECT, locale));

                        Map<String, Object> mapData = new HashMap<>();
                        mapData.put("otpCode", otp.getCode());
                        mapData.put("email", dto.getEmail());
                        if (CommonLanguageEnum.VI.getValue().equals(locale.getLanguage())) {
                            mapData.put("expiredDate", DateTimeUtils.getDateString(otp.getExpiryDate(),
                                    CommonDateTimeConstant.DATETIME_NO_SECOND));
                        } else {
                            mapData.put("expiredDate", DateTimeUtils.getDateString(otp.getExpiryDate(),
                                    CommonDateTimeConstant.DATETIME_NO_SECOND_US));
                        }
                        sendEmailDto.setMapData(mapData);

                        //Do send email
                        SendEmailResultDto sendEmailResultDto = emailService
                                .sendEmailSMTP(sendEmailDto, locale);

                        if (ObjectUtils.isNotEmpty(sendEmailResultDto)
                                && sendEmailResultDto.getIsSuccess()) {
                            result = dto;
                        }
                    }
                }
            }
            case UNKNOWN -> ExceptionUtils.throwApplicationException(
                    CoreException.EXCEPTION_DATA_PROCESSING, locale);
        }

        //Handle process
//        this.handleRegisterProcess(result.getId(), step);

        return result;
    }

    @Override
    public AuthAccountDto getByEmail(String email) {
        Optional<AuthAccount> data = authAccountRepository.findByEmail(email);
        return data.map(authAccount -> objectMapper.convertValue(authAccount,
                AuthAccountDto.class)).orElse(null);
    }

    @Override
    public AuthAccountDto getById(Long id) {
        Optional<AuthAccount> data = authAccountRepository.findData(id);
        return data.map(authAccount -> objectMapper.convertValue(authAccount,
                AuthAccountDto.class)).orElse(null);
    }

    private void handleRegisterProcess(Long userId, AccountStepEnum step) {
        switch (step) {
            case CREATE -> {
                ProcessInstance processInstance = camundaService.startProcess(ProcessConstant.PROCESS_REGISTER, userId);
                if (ObjectUtils.isNotEmpty(processInstance)
                        && StringUtils.isNotEmpty(processInstance.getId())) {
                    pmProcessService.saveProcess(userId, processInstance.getId(), null);
                }
            }
            case OTP -> {
                camundaService.completeTask("3", "Task_0dfv74n");
            }
        }
    }
}
