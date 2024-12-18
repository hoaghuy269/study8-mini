package com.study8.mini.rest.req;

import com.study8.mini.common.constant.CommonPatternConstant;
import com.study8.mini.rest.constant.RestExceptionConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RegisterReq
 * @Date: 2024-11-23
 * @Author: HuyNH
 * @Desc: RegisterReq
 */
@Getter
@Setter
@NoArgsConstructor
public class RegisterReq {
    @Schema(description = "Id", example = "1")
    private Long id;

    @Schema(description = "OTP", example = "OTP")
    private String otp;

    @Schema(description = "Email", example = "dev@study8.com")
    @NotEmpty(message = RestExceptionConstant.EMAIL_CANT_BE_EMPTY)
    @Pattern(regexp = CommonPatternConstant.EMAIL_PATTERN)
    private String email;

    @Schema(description = "Step", example = "STEP")
    @NotEmpty(message = RestExceptionConstant.STEP_CANT_BE_EMPTY)
    private String step;

    @Schema(description = "Password", example = "password")
    @Pattern(regexp = CommonPatternConstant.PASSWORD_PATTERN,
            message = RestExceptionConstant.PASSWORD_NOT_FORMAT)
    private String password;

    @Schema(description = "Username", example = "username")
    @Pattern(regexp = CommonPatternConstant.USERNAME_PATTERN,
            message = RestExceptionConstant.USERNAME_NOT_FORMAT)
    private String username;

    @Schema(description = "Name", example = "John Doe")
    private String name;

    @Schema(description = "Role", example = "ROLE")
    private String role;
}
