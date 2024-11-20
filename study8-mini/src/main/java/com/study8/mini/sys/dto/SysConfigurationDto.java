package com.study8.mini.sys.dto;

import com.study8.mini.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SysConfigurationDto
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfigurationDto
 */
@Getter
@Setter
@NoArgsConstructor
public class SysConfigurationDto extends CommonDto {
    private Long id;
    private String groupCode;
    private String code;
    private String value;
}
