package com.study8.camunda.rest.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NextStepProcessReq
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: NextStepProcessReq
 */
@Getter
@Setter
@NoArgsConstructor
public class CompleteTaskReq {
    @NotEmpty
    private String processInstanceId;

    @NotEmpty
    private String stepCode;
}
