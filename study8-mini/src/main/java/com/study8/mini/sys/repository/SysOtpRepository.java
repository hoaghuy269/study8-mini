package com.study8.mini.sys.repository;

import com.study8.mini.common.repository.CommonRepository;
import com.study8.mini.sys.dto.SysOtpDto;
import com.study8.mini.sys.entity.SysOtp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * SysOtpRepository
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpRepository
 */
@Repository
public interface SysOtpRepository extends CommonRepository<SysOtp, SysOtpDto> {
    @Query("select so from SysOtp so "
            + "where coalesce(so.deletedId, 0) = 0 "
            + "and so.userId = :userId "
            + "and so.active = true "
            + "and so.verified = false "
            + "order by so.id desc")
    Optional<List<SysOtp>> findActiveByUserId(@Param("userId") Long userId);

    @Query("SELECT so FROM SysOtp so " +
            "WHERE so.active = true " +
            "AND so.expiryDate < :now " +
            "AND coalesce(so.deletedId, 0) = 0")
    Page<SysOtp> findExpiredOTP(@Param("now") LocalDateTime now, Pageable pageable);
}
