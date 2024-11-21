package com.study8.mini.sys.repository;

import com.study8.mini.common.repository.CommonRepository;
import com.study8.mini.sys.dto.SysConfigurationDto;
import com.study8.mini.sys.entity.SysConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * SysConfigurationRepository
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfigurationRepository
 */
@Repository
public interface SysConfigurationRepository
        extends CommonRepository<SysConfiguration, SysConfigurationDto> {
    @Query("select sc from SysConfiguration sc "
            + "where sc.groupCode = :groupCode "
            + "and sc.code = :code "
            + "and coalesce(sc.deletedId, 0) = 0")
    Optional<SysConfiguration> findByGroupCodeAndCode(@Param("groupCode") String groupCode,
            @Param("code") String code);
}
