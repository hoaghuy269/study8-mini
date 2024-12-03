package com.study8.camunda.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ProcessDto
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: ProcessDto
 */
@Getter
@Setter
@NoArgsConstructor
public class ProcessDto {
    private String processInstanceId;
    private String processCode;
    private String businessId;
    private String stepCode;
}
