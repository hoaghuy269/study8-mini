package com.study8.mini.auth.service;

import com.study8.mini.configuration.security.UserPrincipal;

/**
 * AuthAccountService
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountService
 */
public interface AuthAccountService {
    UserPrincipal loadUserPrincipal(String username);
}
