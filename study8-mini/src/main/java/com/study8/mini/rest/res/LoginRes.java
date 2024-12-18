package com.study8.mini.rest.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginRes
 * @Date: 2024-11-21
 * @Author: HuyNH
 * @Desc: LoginRes
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRes {
    @Schema(description = "Token", example = "token")
    private String token;
}
