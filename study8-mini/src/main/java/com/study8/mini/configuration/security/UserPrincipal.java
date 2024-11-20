package com.study8.mini.configuration.security;

import com.study8.mini.auth.dto.AuthAccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserPrincipal
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: UserPrincipal
 */
@Getter
@Setter
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String code;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal build(AuthAccountDto account) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(account.getId());
        userPrincipal.setCode(account.getCode());
        userPrincipal.setUsername(account.getUsername());
        userPrincipal.setPassword(account.getPassword());

        List<GrantedAuthority> authorities = account.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        userPrincipal.setAuthorities(authorities);

        return userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
