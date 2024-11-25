package com.study8.mini.pm.service;

/**
 * PmProcessService
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessService
 */
public interface PmProcessService {
    void saveProcess(Long businessId, String processInstanceId, Long userId);
}
