package com.study8.mini.rest.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ForgotPasswordReq
 * @Date: 2024-12-04
 * @Author: HuyNH
 * @Desc: ForgotPasswordReq
 */
@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordReq {
    @Schema(description = "Username", example = "username")
    @NotBlank
    private String username;

    @Schema(description = "OTP Code", example = "OTP")
    private String otpCode;

    @Schema(description = "Step", example = "STEP")
    @NotBlank
    private String step;
}
