package com.study8.mini.auth.repository;

import com.study8.mini.auth.dto.AuthAccountRoleDto;
import com.study8.mini.auth.entity.AuthAccountRole;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

/**
 * AuthAccountRoleRepository
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountRoleRepository
 */
@Repository
public interface AuthAccountRoleRepository
        extends CommonRepository<AuthAccountRole, AuthAccountRoleDto> {
}
