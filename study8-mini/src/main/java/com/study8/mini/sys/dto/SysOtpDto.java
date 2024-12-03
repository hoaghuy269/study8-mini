package com.study8.mini.sys.dto;

import com.study8.mini.common.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * SysOtpDto
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtpDto
 */
@Getter
@Setter
@NoArgsConstructor
public class SysOtpDto extends CommonDto {
    private Long id;
    private String type;
    private Long userId;
    private String code;
    private Boolean active;
    private LocalDateTime sendDate;
    private LocalDateTime expiryDate;
    private Boolean verified;
    private LocalDateTime verifiedDate;
}
