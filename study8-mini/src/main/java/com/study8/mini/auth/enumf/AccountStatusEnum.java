package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * AccountStatusEnum
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: AccountStatusEnum
 */
@Getter
public enum AccountStatusEnum {
    INACTIVE("0"),
    ACTIVE("1"),
    NO_INFO("2"),
    LOCKED("3"),
    NOT_VERIFIED("4"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    AccountStatusEnum(String value) {
        this.value = value;
    }

    public static AccountStatusEnum resolveByValue(String value) {
        for (AccountStatusEnum enumValue : AccountStatusEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
