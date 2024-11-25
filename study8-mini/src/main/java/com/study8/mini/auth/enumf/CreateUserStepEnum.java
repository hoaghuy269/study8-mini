package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * CreateUserStepEnum
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: CreateUserStepEnum
 */
@Getter
public enum CreateUserStepEnum {
    CREATE("CREATE"),
    OTP("OTP"),
    VERIFY("VERIFY"),
    SUBMIT("SUBMIT"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    CreateUserStepEnum(String value) {
        this.value = value;
    }

    public static CreateUserStepEnum resolveByValue(String value) {
        for (CreateUserStepEnum enumValue : CreateUserStepEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
