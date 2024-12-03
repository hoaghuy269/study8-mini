package com.study8.mini.rest.v1.req;

import com.study8.mini.common.constant.CommonPatternConstant;
import com.study8.mini.rest.constant.RestExceptionConstant;
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
    private Long id;

    private String otp;

    @NotEmpty(message = RestExceptionConstant.EMAIL_CANT_BE_EMPTY)
    @Pattern(regexp = CommonPatternConstant.EMAIL_PATTERN)
    private String email;

    @NotEmpty(message = RestExceptionConstant.STEP_CANT_BE_EMPTY)
    private String step;

    @Pattern(regexp = CommonPatternConstant.PASSWORD_PATTERN,
            message = RestExceptionConstant.PASSWORD_NOT_FORMAT)
    private String password;

    @Pattern(regexp = CommonPatternConstant.USERNAME_PATTERN,
            message = RestExceptionConstant.USERNAME_NOT_FORMAT)
    private String username;

    private String name;

    private String role;
}
