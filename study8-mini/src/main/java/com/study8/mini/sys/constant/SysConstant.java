package com.study8.mini.sys.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SysConstant
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SysConstant {
    public static final String DEFAULT_CACHE = "defaultCache";
    public static final String JWT_SECRET_CACHE_KEY = "jwtSecret";
    public static final String JWT_EXPIRATION_CACHE_KEY = "jwtExpiration";
    public static final String JWT = "JWT";
    public static final String JWT_SECRET = "JWT_SECRET";
    public static final String JWT_EXPIRATION = "JWT_EXPIRATION";
    public static final String OTP = "OTP";
    public static final String VERIFY_OTP_EXPIRATION = "VERIFY_OTP_EXPIRATION";
    public static final String EMAIL = "EMAIL";
    public static final String EMAIL_USERNAME = "EMAIL_USERNAME";
    public static final String EMAIL_PASSWORD = "EMAIL_PASSWORD";
    public static final String RESOURCE_EMAIL_TEMPLATES = "email-templates";
    public static final String EMAIL_001_SUBJECT = "EMAIL_001_SUBJECT";
    public static final String PAGINATION = "PAGINATION";
    public static final String PAGE = "PAGE";
    public static final String PAGE_SIZE = "PAGE_SIZE";
    public static final String CAMUNDA = "CAMUNDA";
    public static final String CAMUNDA_ENABLE = "CAMUNDA_ENABLE";
    public static final String CAMUNDA_URL = "CAMUNDA_URL";
    public static final String CAMUNDA_START_PROCESS_URL = "CAMUNDA_START_PROCESS_URL";
    public static final String CAMUNDA_COMPLETE_TASK_URL = "CAMUNDA_COMPLETE_TASK_URL";
}
