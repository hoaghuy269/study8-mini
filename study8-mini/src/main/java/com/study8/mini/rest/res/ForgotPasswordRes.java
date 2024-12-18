package com.study8.mini.rest.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ForgotPasswordRes
 * @Date: 2024-12-04
 * @Author: HuyNH
 * @Desc: ForgotPasswordRes
 */
@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordRes {
    @Schema(description = "Username", example = "username")
    private String username;
}
