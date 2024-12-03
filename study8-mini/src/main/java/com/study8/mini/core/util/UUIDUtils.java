package com.study8.mini.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * UUIDUtils
 * @Date: 2024-06-28
 * @Author: HuyNH
 * @Desc: UUID Utils
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String randomOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
