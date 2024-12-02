package com.study8.camunda.rest.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StartProcessReq
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: StartProcessReq
 */
@Getter
@Setter
@NoArgsConstructor
public class StartProcessReq {
    private String processName;
    private String businessId;
}
