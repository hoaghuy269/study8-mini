package com.study8.mini.rest.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ErrorMessageConstant
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: ErrorMessageConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestExceptionConstant {
    public static final String EMAIL_CANT_BE_EMPTY = "EMAIL_CANT_BE_EMPTY";
    public static final String STEP_CANT_BE_EMPTY = "STEP_CANT_BE_EMPTY";
    public static final String PASSWORD_NOT_FORMAT = "PASSWORD_NOT_FORMAT";
    public static final String USERNAME_NOT_FORMAT = "USERNAME_NOT_FORMAT";
}
