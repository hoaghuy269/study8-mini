package com.study8.mini.common.enumf;

import lombok.Getter;

/**
 * CommonLanguageEnum
 * @Date: 2024-11-27
 * @Author: HuyNH
 * @Desc: CommonLanguageEnum
 */
@Getter
public enum CommonLanguageEnum {
    VI("vi"), // Vietnamese
    EN("en"), // English
    UNKNOWN("UNKNOWN"); // Unknown

    private final String value;

    CommonLanguageEnum(String value) {
        this.value = value;
    }

    public static CommonLanguageEnum resolveByValue(String value) {
        for (CommonLanguageEnum enumValue : CommonLanguageEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
