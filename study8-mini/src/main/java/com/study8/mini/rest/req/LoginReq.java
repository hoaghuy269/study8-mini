package com.study8.mini.rest.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Username", example = "admin")
    @Size(max = 255)
    @NotBlank
    private String username;

    @Schema(description = "Password", example = "password")
    @NotBlank
    private String password;
}
