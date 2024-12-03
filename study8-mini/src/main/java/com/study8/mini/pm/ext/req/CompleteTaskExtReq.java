package com.study8.mini.pm.ext.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CompleteTaskExtReq
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: CompleteTaskExtReq
 */
@Getter
@Setter
@NoArgsConstructor
public class CompleteTaskExtReq {
    private String processInstanceId;
    private String stepCode;
}
