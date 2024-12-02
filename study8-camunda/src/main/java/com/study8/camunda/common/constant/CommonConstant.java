package com.study8.camunda.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * CommonConstant
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: CommonConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstant {
    public static final String MESSAGES_SOURCE = "study8_camunda_messages";
    public static final Integer SUCCESS = 200;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer BAD_REQUEST = 400;

    public static final String MESSAGE_TILE_SUCCESS = "OK";
    public static final String MESSAGE_TITLE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_TITLE_UNAUTHORIZED = "Unauthorized";

    public static final String MESSAGE_SUCCESS = "MESSAGE_SUCCESS";
    public static final String MESSAGE_BAD_REQUEST = "MESSAGE_BAD_REQUEST";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
}
