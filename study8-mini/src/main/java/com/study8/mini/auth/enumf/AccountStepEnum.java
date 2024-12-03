package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * CreateUserStepEnum
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: CreateUserStepEnum
 */
@Getter
public enum AccountStepEnum {
    CREATE("CREATE"),
    OTP("OTP"),
    VERIFY("VERIFY"),
    SUBMIT("SUBMIT"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    AccountStepEnum(String value) {
        this.value = value;
    }

    public static AccountStepEnum resolveByValue(String value) {
        for (AccountStepEnum enumValue : AccountStepEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
