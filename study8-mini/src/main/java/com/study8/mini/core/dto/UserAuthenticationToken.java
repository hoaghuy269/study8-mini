package com.study8.mini.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Locale;

/**
 * UserAuthenticationToken
 * @Date: 2024-12-03
 * @Author: HuyNH
 * @Desc: UserAuthenticationToken
 */
@Getter
@Setter
public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Locale locale;

    public UserAuthenticationToken(Object principal, Object credentials, Locale locale) {
        super(principal, credentials);
        this.locale = locale;
    }

}
