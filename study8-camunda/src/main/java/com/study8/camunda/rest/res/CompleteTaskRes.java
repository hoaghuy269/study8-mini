package com.study8.camunda.rest.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NextStepProcessRes
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: NextStepProcessRes
 */
@Getter
@Setter
@NoArgsConstructor
public class CompleteTaskRes {
    private String processInstanceId;
}
