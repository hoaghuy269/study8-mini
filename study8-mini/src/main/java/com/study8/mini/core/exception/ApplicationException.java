package com.study8.mini.core.exception;

/**
 * CoreApplicationException
 * @Date: 2024-06-11
 * @Author: HuyNH
 * @Desc: Core Application Exception
 */
public class ApplicationException extends Exception {
    public ApplicationException(String errorMessage) {
        super(errorMessage);
    }

    public ApplicationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
