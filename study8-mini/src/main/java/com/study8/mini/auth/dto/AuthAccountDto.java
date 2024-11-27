package com.study8.mini.auth.dto;

import com.study8.mini.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * AuthAccountDto
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountDto
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthAccountDto extends CommonDto {
    private Long id;
    private String username;
    private String code;
    private String email;
    private String password;
    private String status;
    private List<AuthRoleDto> roles;

    private String step;
    private String otp;
}
