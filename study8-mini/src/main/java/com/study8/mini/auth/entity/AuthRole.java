package com.study8.mini.auth.entity;

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
 * AuthRole
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthRole
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "auth_role")
public class AuthRole extends CommonEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
