package com.study8.mini.rest.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * AuthApiConstant
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthApiConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthApiConstant {
    public static final String API_LOGIN = "/login";
    public static final String API_LOGOUT = "/logout";
    public static final String API_REGISTER = "/register";
    public static final String API_FORGOT_PASSWORD = "/forgot-password";
}
