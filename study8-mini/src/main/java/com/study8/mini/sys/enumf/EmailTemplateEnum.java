package com.study8.mini.sys.enumf;

import lombok.Getter;

/**
 * EmailTemplateEnum
 * @Date: 2024-11-27
 * @Author: HuyNH
 * @Desc: EmailTemplateEnum
 */
@Getter
public enum EmailTemplateEnum {
    VERIFY_EMAIL("email_001"),
    FORGOT_PASSWORD_EMAIL("email_002");

    private final String value;

    EmailTemplateEnum(String value) {
        this.value = value;
    }
}
