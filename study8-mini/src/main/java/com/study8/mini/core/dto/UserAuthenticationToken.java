package com.study8.mini.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Locale;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthenticationToken that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(locale, that.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locale);
    }
}

