package com.study8.mini.rest.req;

import com.study8.mini.rest.constant.ErrorMessageConstant;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = ErrorMessageConstant.EMAIL_CANT_BE_EMPTY)
    private String email;

    @NotEmpty(message = ErrorMessageConstant.STEP_CANT_BE_EMPTY)
    private String step;
}
