package com.study8.mini.rest.v1.auth.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginReq
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: Login Request
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginReq {
    @Size(max = 255)
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
