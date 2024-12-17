package com.study8.mini.configuration.constant;

import com.study8.mini.common.constant.CommonApiConstant;
import com.study8.mini.rest.constant.RestApiConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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
    public static final String AUTH_URL = CommonApiConstant.API_V1 + RestApiConstant.API_AUTH + RestApiConstant.API_ALL;
    public static final String SYS_CONFIGURATION_URL = CommonApiConstant.API_V1 + RestApiConstant.API_SYS + RestApiConstant.API_CONFIGURATION;
    public static final String API_DOCS_URL = "/v3/api-docs" + RestApiConstant.API_ALL;
    public static final String SWAGGER_UI_URL = "/swagger-ui" + RestApiConstant.API_ALL;
    public static final String SWAGGER_UI_HTML_URL = "/swagger-ui.html";
    public static final List<String> publicUrls = List.of(
            AUTH_URL,
            SYS_CONFIGURATION_URL,
            API_DOCS_URL,
            SWAGGER_UI_URL,
            SWAGGER_UI_HTML_URL
    );
}
