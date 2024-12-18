package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * ForgotPasswordStepEnum
 * @Date: 2024-12-13
 * @Author: HuyNH
 * @Desc: ForgotPasswordStepEnum
 */
@Getter
public enum ForgotPasswordStepEnum {
    CREATE("CREATE"),
    RESEND("RESEND"),
    VERIFY("VERIFY"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    ForgotPasswordStepEnum(String value) {
        this.value = value;
    }

    public static ForgotPasswordStepEnum resolveByValue(String value) {
        for (ForgotPasswordStepEnum enumValue : ForgotPasswordStepEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
