package com.study8.mini.auth.dto;

import com.study8.mini.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AuthRoleDto
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthRoleDto
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthRoleDto extends CommonDto {
    private Long id;
    private String name;
}
