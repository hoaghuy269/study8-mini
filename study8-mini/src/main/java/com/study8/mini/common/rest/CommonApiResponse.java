package com.study8.mini.common.rest;

import com.study8.mini.common.constant.CommonStatusCodeConstant;
import com.study8.mini.core.constant.CoreConstant;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

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

    public static <T> CommonApiResponse<T> handleSuccess(T data, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle(
                CoreConstant.MESSAGES_SOURCE, locale);
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonStatusCodeConstant.SUCCESS);
        response.setTitle(CommonStatusCodeConstant.MESSAGE_TILE_SUCCESS);
        response.setMessage(messages.getString(CommonStatusCodeConstant.MESSAGE_SUCCESS));
        response.setTime(LocalDateTime.now());
        response.setData(data);

        return response;
    }

    public static <T> CommonApiResponse<T> handleSuccess(T data, String title, String message, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle(
                CoreConstant.MESSAGES_SOURCE, locale);
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonStatusCodeConstant.SUCCESS);
        response.setTitle(messages.getString(title));
        response.setMessage(messages.getString(message));
        response.setTime(LocalDateTime.now());
        response.setData(data);

        return response;
    }

    public static <T> CommonApiResponse<T> handleError(String message) {
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonStatusCodeConstant.BAD_REQUEST);
        response.setTitle(CommonStatusCodeConstant.MESSAGE_TITLE_BAD_REQUEST);
        response.setMessage(message);
        response.setTime(LocalDateTime.now());
        response.setData(null);

        return response;
    }

    public static <T> CommonApiResponse<T> handleAuthError(String message) {
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonStatusCodeConstant.UNAUTHORIZED);
        response.setTitle(CommonStatusCodeConstant.MESSAGE_TITLE_UNAUTHORIZED);
        response.setMessage(message);
        response.setTime(LocalDateTime.now());
        response.setData(null);

        return response;
    }
}
