package com.study8.mini.common.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * CommonDto
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: CommonDto
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class CommonDto {
    private LocalDateTime createdDate;
    private Long createdId;
    private LocalDateTime updatedDate;
    private Long updatedId;
    private LocalDateTime deletedDate;
    private Long deletedId;
}
