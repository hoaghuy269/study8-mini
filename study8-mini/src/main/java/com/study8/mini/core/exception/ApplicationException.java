package com.study8.mini.core.exception;

import lombok.Getter;

/**
 * CoreApplicationException
 * @Date: 2024-06-11
 * @Author: HuyNH
 * @Desc: Core Application Exception
 */
@Getter
public class ApplicationException extends Exception {
    private String errorCode;

    public ApplicationException(String errorMessage) {
        super(errorMessage);
    }

    public ApplicationException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
