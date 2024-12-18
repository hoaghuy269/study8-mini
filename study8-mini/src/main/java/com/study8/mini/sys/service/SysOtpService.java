package com.study8.mini.sys.service;

import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.enumf.OtpTypeEnum;

import java.util.Locale;

/**
 * SysOtpService
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpService
 */
public interface SysOtpService {
    SysOtpDto generateOTP(OtpTypeEnum type, Long userId);

    SysOtpDto getNewestOTP(Long userId);

    boolean verifyOTP(String otp, Long userId, OtpTypeEnum type, Locale locale);
}
