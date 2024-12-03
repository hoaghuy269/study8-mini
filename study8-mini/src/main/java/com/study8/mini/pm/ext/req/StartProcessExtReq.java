package com.study8.mini.pm.ext.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StartProcessExtReq
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: StartProcessExtReq
 */
@Getter
@Setter
@NoArgsConstructor
public class StartProcessExtReq {
    private String processCode;
    private String businessId;
}
