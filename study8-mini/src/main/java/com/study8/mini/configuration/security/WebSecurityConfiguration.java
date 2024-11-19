package com.study8.mini.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * WebSecurityConfiguration
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: WebSecurityConfiguration
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
}
