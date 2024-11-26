package com.study8.mini.auth.validator;

import com.study8.mini.auth.constant.AuthExceptionConstant;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.enumf.AccountStatusEnum;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.core.constant.CoreException;
import com.study8.mini.core.exception.ApplicationException;
import com.study8.mini.core.util.ExceptionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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

    public boolean validateBeforeRegister(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        AuthAccountDto dto = authAccountService.getByEmail(data.getEmail());
        if (ObjectUtils.isNotEmpty(dto)) {
            ExceptionUtils.throwApplicationException(
                    AuthExceptionConstant.AUTH_EXCEPTION_EMAIL_EXISTS, locale);
        }
        return true;
    }

    public boolean validateBeforeSendOTP(AuthAccountDto data, Locale locale)
            throws ApplicationException {
        if (data.getId() == null) {
            ExceptionUtils.throwApplicationException(
                    CoreException.EXCEPTION_DATA_PROCESSING, locale);
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
        return true;
    }
}
