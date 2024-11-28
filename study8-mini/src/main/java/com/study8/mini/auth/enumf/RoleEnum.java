package com.study8.mini.auth.enumf;

import lombok.Getter;

/**
 * RoleEnum
 * @Date: 2024-11-27
 * @Author: HuyNH
 * @Desc: RoleEnum
 */
@Getter
public enum RoleEnum {
    ROLE_ADMIN("ROLE_ADMIN", 1L),
    ROLE_STUDENT("ROLE_STUDENT", 2L),
    ROLE_TEACHER("ROLE_TEACHER", 3L),
    ROLE_VISITOR("ROLE_VISITOR", 4L),
    UNKNOWN("UNKNOWN", 0L)
    ;

    private final String value;
    private final Long id;

    RoleEnum(String value, Long id) {
        this.value = value;
        this.id =  id;
    }

    public static RoleEnum resolveByValue(String value) {
        for (RoleEnum enumValue : RoleEnum.values()) {
            if (enumValue.value.equals(value)) {
                return enumValue;
            }
        }
        return UNKNOWN;
    }
}
