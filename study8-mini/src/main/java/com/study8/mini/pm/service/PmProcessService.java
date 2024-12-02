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
    void startProcess(ProcessCodeEnum processCode, Long businessId);

    void nextStepProcess(String processInstanceId, String stepName);

    PmProcessDto getProcess(ProcessCodeEnum processCode, Long businessId);
}
