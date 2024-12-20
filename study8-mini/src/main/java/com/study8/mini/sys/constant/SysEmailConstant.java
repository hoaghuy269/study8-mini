package com.study8.mini.sys.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SysEmailConstant
 * @Date: 2024-12-12
 * @Author: HuyNH
 * @Desc: SysEmailConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SysEmailConstant {
    public static final String EMAIL_TEMPLATE_RESOURCE = "email-templates";
    public static final String EMAIL = "EMAIL";
    public static final String EMAIL_USERNAME = "EMAIL_USERNAME";
    public static final String EMAIL_PASSWORD = "EMAIL_PASSWORD";
    public static final String EMAIL_002_SUBJECT = "EMAIL_002_SUBJECT";
    public static final String EMAIL_001_SUBJECT = "EMAIL_001_SUBJECT";
    public static final String EMAIL_VALUE_OTP_CODE = "otpCode";
    public static final String EMAIL_VALUE_EMAIL = "email";
    public static final String EMAIL_VALUE_EXPIRED_DATE = "expiredDate";
}
