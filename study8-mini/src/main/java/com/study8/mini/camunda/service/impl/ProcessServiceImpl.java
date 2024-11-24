package com.study8.mini.camunda.service.impl;

import com.study8.mini.camunda.service.ProcessService;
import jakarta.transaction.Transactional;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProcessServiceImpl
 * @Date: 2024-11-24
 * @Author: HuyNH
 * @Desc: ProcessServiceImpl
 */
@Service
@Transactional
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ProcessInstance startProcess(String processName, Long businessId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("loanApproval", businessId.toString());
        return processInstance;
    }
}
