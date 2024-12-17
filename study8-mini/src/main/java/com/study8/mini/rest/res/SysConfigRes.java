package com.study8.mini.rest.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysConfigRes {
    private String groupCode;
    private String code;
    private String value;
}
