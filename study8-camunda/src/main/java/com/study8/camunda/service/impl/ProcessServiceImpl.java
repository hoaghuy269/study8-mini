package com.study8.camunda.service.impl;

import com.study8.camunda.dto.ProcessDto;
import com.study8.camunda.service.ProcessService;
import org.apache.commons.lang3.ObjectUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessServiceImpl
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: ProcessServiceImpl
 */
@Service
@Transactional
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ProcessDto startProcess(ProcessDto dto) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(dto.getProcessCode(), dto.getBusinessId());
        if (ObjectUtils.isNotEmpty(processInstance)) {
            ProcessDto result = new ProcessDto();
            result.setProcessInstanceId(processInstance.getProcessInstanceId());

            return result;
        }
        return null;
    }

    @Override
    public ProcessDto nextStepProcess(ProcessDto dto) {
        String processInstanceId = dto.getProcessInstanceId();
        String step = dto.getStepName();
        return null;
    }
}
