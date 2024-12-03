package com.study8.camunda.rest.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StartProcessRes
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: StartProcessRes
 */
@Getter
@Setter
@NoArgsConstructor
public class StartProcessRes {
    private String processInstanceId;
}
