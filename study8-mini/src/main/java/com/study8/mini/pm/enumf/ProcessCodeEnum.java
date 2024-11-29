package com.study8.mini.pm.enumf;

import lombok.Getter;

/**
 * ProcessCodeEnum
 * @Date: 2024-11-29
 * @Author: HuyNH
 * @Desc: ProcessCodeEnum
 */
@Getter
public enum ProcessCodeEnum {
    PROCESS_REGISTER("PROCESS_REGISTER_v1.1"),
    UNKNOWN("UNKNOWN")
    ;

    private final String value;

    ProcessCodeEnum(String value) {
        this.value = value;
    }
}
