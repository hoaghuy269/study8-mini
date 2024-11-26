package com.study8.mini.sys.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.core.constant.CoreSystem;
import com.study8.mini.core.util.UUIDUtils;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.entity.SysOtp;
import com.study8.mini.sys.enumf.OtpTypeEnum;
import com.study8.mini.sys.repository.SysOtpRepository;
import com.study8.mini.sys.service.SysConfigurationService;
import com.study8.mini.sys.service.SysOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * SysOtpServiceImpl
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpServiceImpl
 */
@Service
@Transactional
public class SysOtpServiceImpl implements SysOtpService {
    @Autowired
    private SysOtpRepository sysOtpRepository;

    @Autowired
    private SysConfigurationService sysConfigurationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SysOtpDto generateOTP(OtpTypeEnum type, Long userId) {
        SysOtpDto result = new SysOtpDto();
        SysOtp entity;

        LocalDateTime today = LocalDateTime.now();

        switch (type) {
            case VERIFY_ACCOUNT -> {
                SysOtp saveData = new SysOtp();
                saveData.setType(OtpTypeEnum.VERIFY_ACCOUNT.getValue());
                saveData.setUserId(userId);
                saveData.setCode(UUIDUtils.randomOTP());
                saveData.setActive(true);
                saveData.setSendDate(today);
                saveData.setExpiryDate(this.getExpiryDate(today));
                saveData.setVerified(false);
                saveData.setVerifiedDate(null);
                saveData.setCreatedDate(today);
                saveData.setCreatedId(CoreSystem.SYSTEM_ID);

                entity = sysOtpRepository.save(saveData);

                result = objectMapper.convertValue(entity, SysOtpDto.class);
            }
            case FORGOT_PASSWORD -> {

            }
        }

        return result;
    }

    private LocalDateTime getExpiryDate(LocalDateTime currentDate) {
        Integer extraTime = sysConfigurationService
                .getIntConfig(SysConstant.OTP, SysConstant.VERIFY_OTP_EXPIRATION);
        return currentDate.plus(Duration
                .ofMillis(extraTime));
    }
}
