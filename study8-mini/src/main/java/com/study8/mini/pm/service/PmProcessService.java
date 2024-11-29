package com.study8.mini.pm.service;

import com.study8.mini.pm.dto.PmProcessDto;
import com.study8.mini.pm.enumf.ProcessCodeEnum;

/**
 * PmProcessService
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessService
 */
public interface PmProcessService {
    void saveProcess(Long businessId, String processInstanceId, Long createdId);

    PmProcessDto getProcess(Long businessId, ProcessCodeEnum process);
}
