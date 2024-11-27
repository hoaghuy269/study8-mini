package com.study8.mini.sys.service;

import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.enumf.OtpTypeEnum;

/**
 * SysOtpService
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpService
 */
public interface SysOtpService {
    SysOtpDto generateOTP(OtpTypeEnum type, Long userId);

    SysOtpDto getNewestOTP(Long userId);
}
