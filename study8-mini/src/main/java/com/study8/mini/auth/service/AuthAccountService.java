package com.study8.mini.auth.service;

import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.configuration.security.UserPrincipal;
import com.study8.mini.core.exception.ApplicationException;

import java.util.Locale;

/**
 * AuthAccountService
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountService
 */
public interface AuthAccountService {
    UserPrincipal loadUserPrincipal(String username);

    AuthAccountDto register(AuthAccountDto dto, Locale locale)
            throws ApplicationException;

    AuthAccountDto getByEmail(String email);

    AuthAccountDto getById(Long id);

    AuthAccountDto getByUsername(String username);

    AuthAccountDto forgotPassword(String username, Locale locale) throws ApplicationException;
}
