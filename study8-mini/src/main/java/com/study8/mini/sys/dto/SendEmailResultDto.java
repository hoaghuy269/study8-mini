package com.study8.mini.sys.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * SendEmailResultDto
 * @Date: 2024-07-18
 * @Author: HuyNH
 * @Desc: SendEmailResultDto
 */
@NoArgsConstructor
@Getter
@Setter
public class SendEmailResultDto {
    private boolean isSuccess;
    private String errorMessage;
    private LocalDateTime time;
}
