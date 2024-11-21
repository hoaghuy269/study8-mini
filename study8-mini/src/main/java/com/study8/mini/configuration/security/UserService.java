package com.study8.mini.configuration.security;

import com.study8.mini.auth.service.AuthAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserService
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: UserService
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private AuthAccountService authAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authAccountService.loadUserPrincipal(username);
    }
}
