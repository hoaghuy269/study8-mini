package com.study8.mini.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AuthAccountRoleDto
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthAccountRoleDto
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthAccountRoleDto {
    private Long accountId;
    private Long roleId;
}
