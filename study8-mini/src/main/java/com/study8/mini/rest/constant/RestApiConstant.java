package com.study8.mini.rest.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ApiConstant
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: ApiConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestApiConstant {
    public static final String API_AUTH = "/auth";
    public static final String API_LOGIN = "/login";
    public static final String API_LOGOUT = "/logout";
    public static final String API_REGISTER = "/register";
    public static final String API_FORGOT_PASSWORD = "/forgot-password";
    public static final String API_RESET_PASSWORD = "/reset-password";
    public static final String API_SYS = "/sys";
    public static final String API_CONFIGURATION = "/configuration";
}
