package com.study8.mini.rest.req;

import com.study8.mini.common.constant.CommonPatternConstant;
import com.study8.mini.rest.constant.RestExceptionConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResetPasswordReq
 * @Date: 2024-12-19
 * @Author: HuyNH
 * @Desc: ResetPasswordReq
 */
@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordReq {
    @Schema(description = "Password", example = "password")
    @Pattern(regexp = CommonPatternConstant.PASSWORD_PATTERN,
            message = RestExceptionConstant.PASSWORD_NOT_FORMAT)
    @NotBlank
    private String password;
}
