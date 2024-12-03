package com.study8.camunda.rest.req;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String processCode;

    @NotEmpty
    private String businessId;
}
