package com.study8.mini.auth.validator;

import com.study8.mini.auth.constant.AuthExceptionConstant;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.enumf.AccountErrCodeEnum;
import com.study8.mini.auth.enumf.AccountStatusEnum;
import com.study8.mini.auth.enumf.RoleEnum;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.core.constant.CoreExceptionConstant;
import com.study8.mini.core.exception.ApplicationException;
import com.study8.mini.core.util.ExceptionUtils;
import com.study8.mini.sys.constant.SysConfigConstant;
import com.study8.mini.sys.constant.SysExceptionConstant;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.enumf.OtpTypeEnum;
import com.study8.mini.sys.service.SysConfigurationService;
import com.study8.mini.sys.service.SysOtpService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * AuthAccountValidator
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: AuthAccountValidator
 */
@Component
public class AuthAccountValidator {
    @Autowired
    @Lazy
    private AuthAccountService authAccountService;

    @Autowired
    private SysOtpService sysOtpService;

    @Autowired
    private SysConfigurationService sysConfigurationService;

    public boolean validateBeforeRegister(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        AuthAccountDto dto = authAccountService.getByEmail(data.getEmail());
        if (ObjectUtils.isNotEmpty(dto)) {
            String status = dto.getStatus();
            if (StringUtils.isNotEmpty(status)
                    && !AccountStatusEnum.NOT_VERIFIED.getValue()
                            .equals(status)) {
                ExceptionUtils.throwApplicationException(
                        AuthExceptionConstant.AUTH_EXCEPTION_EMAIL_EXISTS, locale);
            }
        }
        return true;
    }

    public boolean validateBeforeSendOTP(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        if (data.getId() == null) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }

        AuthAccountDto dto = authAccountService.getById(data.getId());
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS, locale);
        }
        if (!StringUtils.equals(data.getEmail(), dto.getEmail())) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_EMAIL_NOT_CORRECT, locale);
        }
        if (!AccountStatusEnum.NOT_VERIFIED.getValue().equals(dto.getStatus())) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_HAS_BEEN_VERIFIED, locale);
        }

        //Validate OTP
        this.validateSendOTP(data.getId(), locale);

        return true;
    }

    public boolean validateBeforeVerify(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        if (data.getId() == null || StringUtils.isEmpty(data.getOtp())) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }
        AuthAccountDto dto = authAccountService.getById(data.getId());
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS, locale);
        }
        if (!AccountStatusEnum.NOT_VERIFIED.getValue().equals(dto.getStatus())) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_HAS_BEEN_VERIFIED, locale);
        }
        if (!StringUtils.equals(data.getEmail(), dto.getEmail())) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_EMAIL_NOT_CORRECT, locale);
        }

        //Verify OTP
        boolean isOtpValid = sysOtpService.verifyOTP(data.getOtp(), data.getId(), OtpTypeEnum.VERIFY_ACCOUNT, locale);
        if (!isOtpValid) {
            ExceptionUtils.throwApplicationException(
                    SysExceptionConstant.SYS_EXCEPTION_OTP_HAS_NOT_VALID, locale);
        }

        return true;
    }

    public boolean validateBeforeSubmit(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        if (data.getId() == null) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }
        AuthAccountDto dto = authAccountService.getById(data.getId());
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS, locale);
        }
        if (!AccountStatusEnum.NO_INFO.getValue().equals(dto.getStatus())) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_HAS_BEEN_VERIFIED, locale);
        }

        //Validate data
        if (StringUtils.isEmpty(data.getPassword())
                || StringUtils.isEmpty(data.getName())
                || StringUtils.isEmpty(data.getRole())) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }
        RoleEnum roleEnum = RoleEnum.resolveByValue(data.getRole());
        if (RoleEnum.UNKNOWN.equals(roleEnum)) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }

        //Validate username
        if (StringUtils.isNotEmpty(data.getUsername())) {
            AuthAccountDto dtoByUsername = authAccountService.getByUsername(data.getUsername());
            if (ObjectUtils.isNotEmpty(dtoByUsername)) {
                ExceptionUtils.throwApplicationException(
                        AuthExceptionConstant.AUTH_EXCEPTION_USERNAME_EXISTS, locale);
            }
        }

        return true;
    }

    public boolean validateBeforeForgotPassword(AuthAccount account, Locale locale)
            throws ApplicationException {
        if (ObjectUtils.isEmpty(account)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS, locale);
        }

        AccountStatusEnum statusEnum = AccountStatusEnum.resolveByValue(account.getStatus());
        switch (statusEnum) {
        case LOCKED -> ExceptionUtils.throwApplicationException(
                AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_LOCKED,
                AccountErrCodeEnum.ERR_ACCOUNT_LOCKED.getValue(), locale);
        case NO_INFO -> ExceptionUtils.throwApplicationException(
                AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NO_INFO,
                AccountErrCodeEnum.ERR_ACCOUNT_LOCKED.getValue(), locale);
        case NOT_VERIFIED -> ExceptionUtils.throwApplicationException(
                AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_VERIFIED,
                AccountErrCodeEnum.ERR_ACCOUNT_LOCKED.getValue(), locale);
        case INACTIVE -> ExceptionUtils.throwApplicationException(
                AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_INACTIVE,
                AccountErrCodeEnum.ERR_ACCOUNT_INACTIVE.getValue(), locale);
        case UNKNOWN -> ExceptionUtils.throwApplicationException(
                CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }

        this.validateSendOTP(account.getId(), locale);

        return true;
    }

    public boolean validateBeforeVerify(AuthAccount account, String otp, Locale locale)
            throws ApplicationException {
        if (StringUtils.isEmpty(otp)) {
            ExceptionUtils.throwApplicationException(
                    CoreExceptionConstant.EXCEPTION_DATA_PROCESSING, locale);
        }

        boolean otpValidated = sysOtpService.verifyOTP(otp, account.getId(), OtpTypeEnum.FORGOT_PASSWORD, locale);
        if (!otpValidated) {
            ExceptionUtils.throwApplicationException(
                    SysExceptionConstant.SYS_EXCEPTION_OTP_HAS_NOT_VALID, locale);
        }
        return true;
    }

    public boolean validateBeforeResetPassword(AuthAccount account, Locale locale)
            throws ApplicationException {
        if (ObjectUtils.isEmpty(account)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS, locale);
        }
        return true;
    }

    public void validateSendOTP(Long accountId, Locale locale)
            throws ApplicationException {
        SysOtpDto newestOTP = sysOtpService.getNewestOTP(accountId);
        if (ObjectUtils.isNotEmpty(newestOTP) && newestOTP.getSendDate() != null) {
            int nextTimeOtp = sysConfigurationService.getIntConfig(SysConfigConstant.OTP, SysConfigConstant.OTP_TIME_INTERVAL);

            LocalDateTime nextAllowed = newestOTP.getSendDate().plus(Duration.ofMillis(nextTimeOtp));
            LocalDateTime now = LocalDateTime.now();

            if (now.isBefore(nextAllowed)) {
                String[] errors = new String[] {
                        String.valueOf(Duration.between(now, nextAllowed).getSeconds())
                };
                ExceptionUtils.throwApplicationException(
                        SysExceptionConstant.SYS_EXCEPTION_OTP_HAS_BEEN_SENT, locale, errors);
            }
        }
    }
}
