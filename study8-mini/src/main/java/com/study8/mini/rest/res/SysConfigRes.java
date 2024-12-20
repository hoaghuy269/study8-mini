package com.study8.mini.rest.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SysConfigRes
 * @Date: 2024-12-17
 * @Author: HuyNH
 * @Desc: SysConfigRes
 */
@Getter
@Setter
@NoArgsConstructor
public class SysConfigRes {
    private String groupCode;
    private String code;
    private String value;
}
