package com.study8.mini.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * CommonEntity
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: CommonEntity
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class CommonEntity {
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_id")
    private Long createdId;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "updated_id")
    private Long updatedId;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "deleted_id")
    private Long deletedId = 0L;
}
