package com.study8.mini.configuration.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * AuthenticationService
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthenticationService
 */
@Service
public class AuthenticationService implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
