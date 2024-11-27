package com.study8.mini.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * CommonPatternConstant
 * @Date: 2024-06-28
 * @Author: HuyNH
 * @Desc: CommonPatternConstant
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonPatternConstant {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
}
