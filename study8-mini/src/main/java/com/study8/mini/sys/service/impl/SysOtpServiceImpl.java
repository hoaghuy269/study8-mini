package com.study8.mini.sys.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study8.mini.auth.constant.AuthExceptionConstant;
import com.study8.mini.core.constant.CoreSystem;
import com.study8.mini.core.exception.ApplicationException;
import com.study8.mini.core.util.ExceptionUtils;
import com.study8.mini.core.util.UUIDUtils;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.constant.SysExceptionConstant;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.entity.SysOtp;
import com.study8.mini.sys.enumf.OtpTypeEnum;
import com.study8.mini.sys.repository.SysOtpRepository;
import com.study8.mini.sys.service.SysConfigurationService;
import com.study8.mini.sys.service.SysOtpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * SysOtpServiceImpl
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpServiceImpl
 */
@Service
@Transactional
@Slf4j
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

    @Override
    public SysOtpDto getNewestOTP(Long userId) {
        Optional<List<SysOtp>> listData = sysOtpRepository.findActiveByUserId(userId);
        if (listData.isPresent()) {
            SysOtp result = listData.get().stream()
                    .max(Comparator.comparing(SysOtp::getId))
                    .orElse(null);
            return objectMapper.convertValue(result, SysOtpDto.class);
        }
        return null;
    }

    @Override
    public boolean verifyOTP(String otp, Long userId, Locale locale) {
        LocalDateTime today = LocalDateTime.now();

        Optional<SysOtp> data = sysOtpRepository.findByCodeAndUserId(otp, userId);
        if (data.isEmpty()) {
            return false;
        } else {
            SysOtp entity = data.get();
            entity.setActive(false);
            entity.setVerified(true);
            entity.setVerifiedDate(today);
            entity.setDeletedId(CoreSystem.SYSTEM_ID);
            entity.setDeletedDate(today);

            sysOtpRepository.save(entity);
        }
        return true;
    }

    @Scheduled(fixedRate = 10800000) //Run every 3 hours
    public void deleteExpiredOTPJob() {
        LocalDateTime currentDate = LocalDateTime.now();
        Map<String, String> pagination = sysConfigurationService.getMapConfig(SysConstant.PAGINATION);
        int page = Integer.parseInt(pagination.get(SysConstant.PAGE));
        int pageSize = Integer.parseInt(pagination.get(SysConstant.PAGE_SIZE));

        log.info("SysOtpServiceImpl (Job) | deleteExpiredOTPJob | Start task");

        Page<SysOtp> systemOTPPage;
        try {
            do {
                Pageable pageable = PageRequest
                        .of(page, pageSize);
                systemOTPPage = sysOtpRepository.findExpiredOTP(currentDate, pageable);

                List<SysOtp> systemOTPUpdated = systemOTPPage.getContent();
                systemOTPUpdated.forEach(otp -> {
                    otp.setActive(false);
                    otp.setDeletedId(CoreSystem.SYSTEM_ID);
                    otp.setDeletedDate(currentDate);
                });

                sysOtpRepository.saveAll(systemOTPUpdated);

                page++;
            } while (systemOTPPage.hasNext());
        } catch (Exception e) {
            log.error("SysOtpServiceImpl | deleteExpiredOTPJob", e);
        }

        log.info("SysOtpServiceImpl (Job) | deleteExpiredOTPJob | End task");
    }

    private LocalDateTime getExpiryDate(LocalDateTime currentDate) {
        Integer extraTime = sysConfigurationService
                .getIntConfig(SysConstant.OTP, SysConstant.VERIFY_OTP_EXPIRATION);
        return currentDate.plus(Duration
                .ofMillis(extraTime));
    }
}
