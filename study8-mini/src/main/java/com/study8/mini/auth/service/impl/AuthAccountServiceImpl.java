package com.study8.mini.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.auth.repository.AuthAccountRepository;
import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.auth.service.AuthRoleService;
import com.study8.mini.camunda.constant.ProcessConstant;
import com.study8.mini.camunda.service.ProcessService;
import com.study8.mini.configuration.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private ProcessService processService;

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

    @Override
    public AuthAccountDto register(AuthAccountDto dto) {
        LocalDateTime today = LocalDateTime.now();
        AuthAccount entity = new AuthAccount();

        AuthAccountDto result = new AuthAccountDto();
        if (dto.getId() == null) {
            entity.setEmail(dto.getEmail());
            entity.setUsername("GYUdsagdu");
            entity.setCode("HGGYU");
            entity.setCreatedDate(today);
            entity.setStatus("1");
            entity.setCreatedId(1L);

            AuthAccount newEntity = authAccountRepository.save(entity);
            this.handleRegisterProcess(newEntity.getId());
        }
        return null;
    }

    private void handleRegisterProcess(Long businessId) {
        ProcessInstance processInstance = processService.startProcess(ProcessConstant.PROCESS_REGISTER, businessId);
        String id = processInstance.getId();
    }
}
