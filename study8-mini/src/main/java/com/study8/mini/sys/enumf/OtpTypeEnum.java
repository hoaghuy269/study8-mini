package com.study8.mini.sys.enumf;

import lombok.Getter;

/**
 * OtpTypeEnum
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: OtpTypeEnum
 */
@Getter
public enum OtpTypeEnum {
    VERIFY_ACCOUNT("VERIFY_ACCOUNT"),
    FORGOT_PASSWORD("FORGOT_PASSWORD")
    ;

    private final String value;

    OtpTypeEnum(String value) {
        this.value = value;
    }
}
