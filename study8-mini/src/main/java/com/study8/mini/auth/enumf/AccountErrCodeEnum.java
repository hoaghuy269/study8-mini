package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * AccountErrCodeEnum
 * @Date: 2024-12-06
 * @Author: HuyNH
 * @Desc: AccountErrCodeEnum
 */
@Getter
public enum AccountErrCodeEnum {
    ERR_ACCOUNT_LOCKED("ERR_ACC_001"),
    ERR_ACCOUNT_NO_INFO("ERR_ACC_002"),
    ERR_ACCOUNT_NOT_VERIFIED("ERR_ACC_003"),
    ERR_ACCOUNT_INACTIVE("ERR_ACC_004"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    AccountErrCodeEnum(String value) {
        this.value = value;
    }
}
