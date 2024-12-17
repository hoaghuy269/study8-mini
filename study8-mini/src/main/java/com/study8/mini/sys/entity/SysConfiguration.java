package com.study8.mini.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * SysConfiguration
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: SysConfiguration
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sys_configuration")
public class SysConfiguration
        extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_code")
    private String groupCode;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "public_flag")
    private Boolean publicFlag;
}
