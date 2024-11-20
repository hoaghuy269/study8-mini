package com.study8.mini.auth.service.impl;

import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.repository.AuthAccountRepository;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.configuration.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthAccountServiceImpl
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountServiceImpl
 */
@Service
@Transactional
@Slf4j
public class AuthAccountServiceImpl implements AuthAccountService {
    @Autowired
    private AuthAccountRepository authAccountRepository;

    @Override
    public UserPrincipal loadUserPrincipal(String username) {
        AuthAccount account = authAccountRepository.findByUsername(username)
                .orElse(null);
        if (ObjectUtils.isNotEmpty(account)) {
            //TODO: Build principal
            return UserPrincipal.build();
        }
        return null;
    }
}
