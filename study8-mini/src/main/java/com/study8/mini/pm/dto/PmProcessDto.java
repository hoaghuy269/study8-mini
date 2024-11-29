package com.study8.mini.pm.dto;

import com.study8.mini.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PmProcessDto
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcessDto
 */
@Getter
@Setter
@NoArgsConstructor
public class PmProcessDto extends CommonDto {
    private Long id;
    private String code;
    private Long businessId;
    private String processId;
}
