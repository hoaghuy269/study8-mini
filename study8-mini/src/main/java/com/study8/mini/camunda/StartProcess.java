package com.study8.mini.camunda;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartProcess {
    @Autowired
    private RuntimeService runtimeService;

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("loanApproval");
    }

}
