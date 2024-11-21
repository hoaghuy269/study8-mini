package com.study8.mini.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.repository.AuthAccountRepository;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.auth.service.AuthRoleService;
import com.study8.mini.configuration.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserPrincipal loadUserPrincipal(String username) {
        Optional<AuthAccount> account = authAccountRepository.findByUsername(username);
        if (account.isPresent()) {
            AuthAccount accountEntity = account.get();
            AuthAccountDto accountDto = objectMapper.convertValue(accountEntity, AuthAccountDto.class);

            //Roles
            List<AuthRoleDto> roles = authRoleService.getRoles(accountEntity.getId());
            accountDto.setRoles(roles);

            return UserPrincipal.build(accountDto);
        }
        return null;
    }
}
