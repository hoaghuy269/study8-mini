package com.study8.mini.camunda.service;

import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * ProcessService
 * @Date: 2024-11-24
 * @Author: HuyNH
 * @Desc: ProcessService
 */
public interface CamundaService {
    ProcessInstance startProcess(String processName, Long businessId);

    void completeTask(String processId, Long businessId, String taskDefinitionKey);
}
