package com.study8.mini.common.rest;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * CommonApiRest
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: CommonApiRest
 */
@Getter
@Setter
public class CommonApiResponse<T> {
    private int statusCode;
    private String title;
    private String message;
    private LocalDateTime time;
    private T data;
}
