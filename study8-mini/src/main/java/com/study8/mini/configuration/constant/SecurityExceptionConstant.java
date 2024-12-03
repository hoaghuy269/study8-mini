package com.study8.mini.configuration.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SecurityExceptionConstant
 * @Date: 2024-12-03
 * @Author: HuyNH
 * @Desc: SecurityExceptionConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityExceptionConstant {
    public static final String SECURITY_EXCEPTION_PASSWORD_INCORRECT = "SECURITY_EXCEPTION_PASSWORD_INCORRECT";
    public static final String SECURITY_EXCEPTION_USERNAME_INCORRECT = "SECURITY_EXCEPTION_USERNAME_INCORRECT";
    public static final String SECURITY_EXCEPTION_ACCOUNT_INACTIVE = "SECURITY_EXCEPTION_ACCOUNT_INACTIVE";
    public static final String SECURITY_EXCEPTION_ACCOUNT_LOCKED = "SECURITY_EXCEPTION_ACCOUNT_LOCKED";
    public static final String SECURITY_EXCEPTION_ACCOUNT_NOT_VERIFIED = "SECURITY_EXCEPTION_ACCOUNT_NOT_VERIFIED";
    public static final String SECURITY_EXCEPTION_ACCOUNT_NO_INFO = "SECURITY_EXCEPTION_ACCOUNT_NO_INFO";
}
