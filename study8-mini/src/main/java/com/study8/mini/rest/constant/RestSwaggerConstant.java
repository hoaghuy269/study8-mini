package com.study8.mini.rest.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * RestSwaggerConstant
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: RestSwaggerConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestSwaggerConstant {
    //Auth Rest
    public static final String AUTH_REST_TAG = "Authentication APIs";
    public static final String API_LOGIN_TAG = "Login API";
    public static final String API_REGISTER_TAG = "Register API";
    public static final String API_LOGOUT_TAG = "Logout API";
    public static final String API_FORGOT_PASSWORD_TAG = "Forgot Password API";

    //Sys Rest
    public static final String SYS_REST_TAG = "System APIs";
    public static final String API_CONFIGURATION_TAG = "System Configuration API";
}
