package com.study8.camunda.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Optional;

/**
 * LanguageUtils
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: LanguageUtils
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageUtils {
    public static Locale getLanguageFromHeader(HttpServletRequest request) {
        String acceptLanguage = Optional.ofNullable(request.getHeader(CoreConstant.ACCEPT_LANGUAGE))
                .orElse(CoreConstant.VI);
        if (acceptLanguage.contains(CoreConstant.VI)) {
            return Locale.forLanguageTag(CoreConstant.VI);
        } else {
            return Locale.ENGLISH;
        }
    }

}
