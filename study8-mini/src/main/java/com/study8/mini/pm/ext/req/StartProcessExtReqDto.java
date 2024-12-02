package com.study8.mini.pm.ext.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StartProcessExtReqDto
 * @Date: 2024-12-02
 * @Author: HuyNH
 * @Desc: StartProcessExtReqDto
 */
@Getter
@Setter
@NoArgsConstructor
public class StartProcessExtReqDto {
    private String processCode;
    private String businessId;
}
