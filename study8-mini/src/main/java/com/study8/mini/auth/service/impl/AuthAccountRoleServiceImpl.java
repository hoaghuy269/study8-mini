package com.study8.mini.auth.service.impl;

import com.study8.mini.auth.entity.AuthAccountRole;
import com.study8.mini.auth.enumf.RoleEnum;
import com.study8.mini.auth.repository.AuthAccountRoleRepository;
import com.study8.mini.auth.service.AuthAccountRoleService;
import com.study8.mini.core.constant.CoreSystem;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * AuthAccountRoleServiceImpl
 * @Date: 2024-11-27
 * @Author: HuyNH
 * @Desc: AuthAccountRoleServiceImpl
 */
@Service
@Transactional
public class AuthAccountRoleServiceImpl implements AuthAccountRoleService {
    @Autowired
    private AuthAccountRoleRepository authAccountRoleRepository;

    @Override
    public void saveRole(Long userId, RoleEnum role) {
        List<AuthAccountRole> dataList = authAccountRoleRepository.findByAccountId(userId)
                .orElse(Collections.emptyList());
        boolean isNewRole = true;

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (AuthAccountRole data : dataList) {
                if (data.getRoleId().equals(role.getId())) {
                    isNewRole = false;
                    break;
                }
                data.setDeletedId(CoreSystem.SYSTEM_ID);
                data.setDeletedDate(LocalDateTime.now());
            }

            authAccountRoleRepository.saveAll(dataList);
        }

        if (isNewRole) {
            AuthAccountRole saveEntity = new AuthAccountRole();
            saveEntity.setAccountId(userId);
            saveEntity.setRoleId(role.getId());
            saveEntity.setCreatedId(CoreSystem.SYSTEM_ID);
            saveEntity.setCreatedDate(LocalDateTime.now());

            //Do save
            authAccountRoleRepository.save(saveEntity);
        }
    }

}
