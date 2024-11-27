package com.study8.mini.auth.dto;

import com.study8.mini.common.dto.CommonDto;
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
public class AuthAccountRoleDto
        extends CommonDto {
    private Long id;
    private Long accountId;
    private Long roleId;
}
