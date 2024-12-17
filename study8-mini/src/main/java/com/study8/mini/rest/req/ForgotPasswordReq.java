package com.study8.mini.rest.req;

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
    private String username;
    private String otpCode;
    private String step;
}
