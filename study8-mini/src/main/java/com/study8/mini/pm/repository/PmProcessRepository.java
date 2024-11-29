package com.study8.mini.pm.repository;

import com.study8.mini.pm.dto.PmProcessDto;
import com.study8.mini.pm.entity.PmProcess;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PmProcessRepository
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessRepository
 */
@Repository
public interface PmProcessRepository
        extends CommonRepository<PmProcess, PmProcessDto> {
    @Query("select e from PmProcess e "
            + "where e.businessId = :businessId "
            + "and e.code = :code "
            + "and coalesce(e.deletedId, 0) = 0")
    Optional<PmProcess> findByBusinessIdAndCode(@Param("businessId") Long businessId, @Param("code") String code);
}
