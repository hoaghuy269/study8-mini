package com.study8.mini.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * CommonStatusCodeConstant
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: CommonStatusCodeConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonStatusCodeConstant {
    public static final Integer SUCCESS = 200;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer BAD_REQUEST = 400;

    public static final String MESSAGE_SUCCESS = "MESSAGE_SUCCESS";
    public static final String MESSAGE_BAD_REQUEST = "MESSAGE_BAD_REQUEST";
    public static final String MESSAGE_UNAUTHORIZED = "MESSAGE_UNAUTHORIZED";
}
