package com.study8.mini.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.repository.AuthRoleRepository;
import com.study8.mini.auth.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AuthRoleServiceImpl implements AuthRoleService {
    @Autowired
    private AuthRoleRepository authRoleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<AuthRoleDto> getRoles(Long accountId) {
        return authRoleRepository.findByAccountId(accountId)
                .map(authRoles -> authRoles.stream()
                        .map(authRole -> objectMapper.convertValue(authRole, AuthRoleDto.class))
                        .toList()
                )
                .orElse(Collections.emptyList());
    }


}
