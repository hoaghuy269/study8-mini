package com.study8.mini.sys.repository;

import com.study8.mini.common.repository.CommonRepository;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.entity.SysOtp;
import org.springframework.stereotype.Repository;

/**
 * SysOtpRepository
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpRepository
 */
@Repository
public interface SysOtpRepository extends CommonRepository<SysOtp, SysOtpDto> {
}
