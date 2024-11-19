package com.study8.mini.common.util;

import com.study8.mini.configuration.constant.SecurityConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * JwtUtils
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: JwtUtils
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {
    public static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(SecurityConstant.AUTHORIZATION);
        if (StringUtils.isNotEmpty(headerAuth)
                && headerAuth.startsWith(SecurityConstant.BEARER)) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
