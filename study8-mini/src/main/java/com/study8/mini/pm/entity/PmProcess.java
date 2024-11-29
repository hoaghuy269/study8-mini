package com.study8.mini.pm.entity;

import com.study8.mini.common.entity.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PmProcess
 * @Date: 2024-11-25
 * @Author: HuyNH
 * @Desc: PmProcess
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pm_process")
public class PmProcess extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "business_id")
    private Long businessId;

    @Column(name = "process_id")
    private String processId;
}
