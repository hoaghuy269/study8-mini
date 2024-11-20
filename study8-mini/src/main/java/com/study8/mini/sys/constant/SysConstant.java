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
}
