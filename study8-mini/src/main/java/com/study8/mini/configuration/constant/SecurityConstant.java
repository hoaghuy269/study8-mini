package com.study8.mini.configuration.constant;

import com.study8.mini.rest.constant.RestApiConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SecurityConstant
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SecurityConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstant {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String CAMUNDA_URL = RestApiConstant.API_CAMUNDA + RestApiConstant.API_ALL;
    public static final String AUTH_URL = RestApiConstant.API_AUTH + RestApiConstant.API_ALL;
}
