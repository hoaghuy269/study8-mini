package com.study8.mini.auth.service;

import com.study8.mini.auth.dto.AuthRoleDto;

import java.util.List;

public interface AuthRoleService {
    List<AuthRoleDto> getRoles(Long accountId);
}
