package com.study8.mini.camunda.service.impl;

import com.study8.mini.camunda.service.CamundaService;
import jakarta.transaction.Transactional;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProcessServiceImpl
 * @Date: 2024-11-24
 * @Author: HuyNH
 * @Desc: ProcessServiceImpl
 */
@Service
@Transactional
public class CamundaServiceImpl implements CamundaService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public ProcessInstance startProcess(String processName, Long businessId) {
        return runtimeService.startProcessInstanceByKey(processName, businessId.toString());
    }

    @Override
    public void completeTask(String businessId, String taskDefinitionKey) {
        String processInstanceId = getProcessByBusinessId(Long.parseLong(businessId));

        if (processInstanceId != null) {
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .taskDefinitionKey(taskDefinitionKey)
                    .list();

            if (!tasks.isEmpty()) {
                Task task = tasks.get(0);
                taskService.complete(task.getId());
            }
        }
    }

    public String getProcessByBusinessId(Long businessId) {
        return runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessId.toString())
                .singleResult()
                .getId();
    }
}
