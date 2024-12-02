package com.study8.camunda.common.rest;

import com.study8.camunda.common.constant.CommonConstant;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
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
    private List<String> errorMessages;
    private LocalDateTime time;
    private T data;

    public static <T> CommonApiResponse<T> handleSuccess(T data, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle(
                CommonConstant.MESSAGES_SOURCE, locale);
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonConstant.SUCCESS);
        response.setTitle(CommonConstant.MESSAGE_TILE_SUCCESS);
        response.setMessage(messages.getString(CommonConstant.MESSAGE_SUCCESS));
        response.setErrorMessages(null);
        response.setTime(LocalDateTime.now());
        response.setData(data);

        return response;
    }

    public static <T> CommonApiResponse<T> handleError(String message, HttpServletResponse httpServletResponse) {
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonConstant.BAD_REQUEST);
        response.setTitle(CommonConstant.MESSAGE_TITLE_BAD_REQUEST);
        response.setMessage(message);
        response.setErrorMessages(null);
        response.setTime(LocalDateTime.now());
        response.setData(null);

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return response;
    }

    public static <T> CommonApiResponse<T> handleAuthError(String message) {
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonConstant.UNAUTHORIZED);
        response.setTitle(CommonConstant.MESSAGE_TITLE_UNAUTHORIZED);
        response.setMessage(message);
        response.setErrorMessages(null);
        response.setTime(LocalDateTime.now());
        response.setData(null);

        return response;
    }

    public static <T> CommonApiResponse<T> handleBindingResult(BindingResult bindingResult, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle(
                CommonConstant.MESSAGES_SOURCE, locale);
        CommonApiResponse<T> response = new CommonApiResponse<>();

        response.setStatusCode(CommonConstant.BAD_REQUEST);
        response.setTitle(CommonConstant.MESSAGE_TITLE_BAD_REQUEST);
        response.setMessage(messages.getString(CommonConstant.MESSAGE_BAD_REQUEST));
        response.setTime(LocalDateTime.now());
        response.setData(null);

        List<String> errorMessages = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String messageKey = fieldError.getDefaultMessage();
                    if (StringUtils.isNotEmpty(messageKey)
                            && messages.containsKey(messageKey)) {
                        return messages.getString(messageKey);
                    } else {
                        return fieldError.getDefaultMessage();
                    }
                })
                .toList();
        response.setErrorMessages(errorMessages);

        return response;
    }
}
