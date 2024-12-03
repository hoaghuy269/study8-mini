package com.study8.mini.configuration.security;

import com.study8.mini.auth.enumf.AccountStatusEnum;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.configuration.constant.SecurityExceptionConstant;
import com.study8.mini.core.dto.UserAuthenticationToken;
import com.study8.mini.core.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * AuthenticationService
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthenticationService
 */
@Service
@Slf4j
public class AuthenticationService implements AuthenticationManager {
    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof UserAuthenticationToken userAuthentication)) {
            log.error("AuthenticationService | authenticate");
            throw new BadCredentialsException("Authentication type not valid");
        }

        Locale locale = userAuthentication.getLocale();
        String username = userAuthentication.getName();
        String password = userAuthentication.getCredentials().toString();

        UserPrincipal userPrincipal = authAccountService.loadUserPrincipal(username);

        //Check username
        if (ObjectUtils.isEmpty(userPrincipal)) {
            String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_USERNAME_INCORRECT, locale);
            throw new BadCredentialsException(message);
        }

        //Check status
        AccountStatusEnum statusEnum = AccountStatusEnum.resolveByValue(userPrincipal.getStatus());
        switch (statusEnum) {
            case INACTIVE -> {
                String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_ACCOUNT_INACTIVE, locale);
                throw new BadCredentialsException(message);
            }
            case LOCKED -> {
                String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_ACCOUNT_LOCKED, locale);
                throw new BadCredentialsException(message);
            }
            case NOT_VERIFIED -> {
                String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_ACCOUNT_NOT_VERIFIED, locale);
                throw new BadCredentialsException(message);
            }
            case NO_INFO -> {
                String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_ACCOUNT_NO_INFO, locale);
                throw new BadCredentialsException(message);
            }
            default -> {
                log.error("Authentication | authenticate | Error Enum");
            }
        }

        //Check password
        if (!passwordEncoder.matches(password, userPrincipal.getPassword())) {
            String message = ResourceUtils.getMessage(SecurityExceptionConstant.SECURITY_EXCEPTION_PASSWORD_INCORRECT, locale);
            throw new BadCredentialsException(message);
        }

        return new UsernamePasswordAuthenticationToken(
                userPrincipal,
                password,
                userPrincipal.getAuthorities()
        );
    }
}
