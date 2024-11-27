package com.study8.mini.pm.repository;

import com.study8.mini.pm.dto.PmProcessDto;
import com.study8.mini.pm.entity.PmProcess;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

/**
 * PmProcessRepository
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessRepository
 */
@Repository
public interface PmProcessRepository
        extends CommonRepository<PmProcess, PmProcessDto> {
}
