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
 * AuthAccountRole
 * @Date: 2024-11-20
 * @Author: HuyNH
 * @Desc: AuthAccountRole
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "auth_account_role")
public class AuthAccountRole extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "role_id")
    private Long roleId;
}
