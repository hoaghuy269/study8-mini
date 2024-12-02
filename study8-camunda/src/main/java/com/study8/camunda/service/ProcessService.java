package com.study8.camunda.service;

import com.study8.camunda.dto.ProcessDto;

/**
 * ProcessService
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: ProcessService
 */
public interface ProcessService {
    ProcessDto startProcess(ProcessDto dto);
}
