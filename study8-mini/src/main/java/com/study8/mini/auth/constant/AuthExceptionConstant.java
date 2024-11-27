package com.study8.mini.auth.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AuthException
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: AuthException
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthExceptionConstant {
    public static final String AUTH_EXCEPTION_EMAIL_EXISTS = "AUTH_EXCEPTION_EMAIL_EXISTS";
    public static final String AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS = "AUTH_EXCEPTION_ACCOUNT_NOT_EXISTS";
    public static final String AUTH_EXCEPTION_ACCOUNT_HAS_BEEN_VERIFIED = "AUTH_EXCEPTION_ACCOUNT_HAS_BEEN_VERIFIED";
}