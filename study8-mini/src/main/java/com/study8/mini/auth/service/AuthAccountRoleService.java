package com.study8.mini.auth.service;

import com.study8.mini.auth.enumf.RoleEnum;

/**
 * AuthAccountRoleService
 * @Date: 2024-11-27
 * @Author: HuyNH
 * @Desc: AuthAccountRoleService
 */
public interface AuthAccountRoleService {
    void saveRole(Long userId, RoleEnum role);
}
