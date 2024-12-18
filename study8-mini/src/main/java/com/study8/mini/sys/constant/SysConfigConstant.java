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
public class SysConfigConstant {
    public static final String OTP = "OTP";
    public static final String OTP_TIME_INTERVAL = "OTP_TIME_INTERVAL";
    public static final String VERIFY_OTP_EXPIRATION = "VERIFY_OTP_EXPIRATION";
    public static final String JWT = "JWT";
    public static final String JWT_FP_SECRET = "JWT_FP_SECRET";
    public static final String JWT_FP_EXPIRATION = "JWT_FP_EXPIRATION";
}
