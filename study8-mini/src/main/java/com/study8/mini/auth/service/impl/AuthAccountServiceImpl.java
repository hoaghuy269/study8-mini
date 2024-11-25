package com.study8.mini.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.enumf.AccountStatusEnum;
import com.study8.mini.auth.enumf.CreateUserStepEnum;
import com.study8.mini.auth.repository.AuthAccountRepository;
import com.study8.mini.pm.service.PmProcessService;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.auth.service.AuthRoleService;
import com.study8.mini.camunda.constant.ProcessConstant;
import com.study8.mini.camunda.service.ProcessService;
import com.study8.mini.configuration.security.UserPrincipal;
import com.study8.mini.core.constant.CoreException;
import com.study8.mini.core.constant.CoreSystem;
import com.study8.mini.core.exception.ApplicationException;
import com.study8.mini.core.util.ExceptionUtils;
import com.study8.mini.core.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
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
    private ProcessService processService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PmProcessService pmProcessService;

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

        CreateUserStepEnum step = CreateUserStepEnum.resolveByValue(dto.getStep());
        switch (step) {
            case CREATE -> {
                AuthAccount entity = new AuthAccount();
                entity.setEmail(dto.getEmail());
                entity.setCode(UUIDUtils.randomUUID());
                entity.setStatus(AccountStatusEnum.NOT_VERIFIED.getValue());
                entity.setCreatedDate(today);
                entity.setCreatedId(CoreSystem.SYSTEM_ID);

                //Do create account
                newEntity = authAccountRepository.save(entity);

                result = objectMapper.convertValue(newEntity, AuthAccountDto.class);
            }
            case OTP -> {
                if (dto.getId() == null) {
                    ExceptionUtils.throwApplicationException(
                            CoreException.EXCEPTION_DATA_PROCESSING, locale);
                }

            }
            case UNKNOWN -> ExceptionUtils.throwApplicationException(CoreException.EXCEPTION_DATA_PROCESSING, locale);
        }

        //Handle process
        this.handleRegisterProcess(result.getId(), step);

        return result;
    }

    private void handleRegisterProcess(Long userId, CreateUserStepEnum step) {
        switch (step) {
            case CREATE -> {
                ProcessInstance processInstance = processService.startProcess(ProcessConstant.PROCESS_REGISTER, userId);
                if (ObjectUtils.isNotEmpty(processInstance)) {
                    pmProcessService
                }
            }
            case OTP -> {
                processService.completeTask("3", "Task_0dfv74n");
            }
        }
    }
}
