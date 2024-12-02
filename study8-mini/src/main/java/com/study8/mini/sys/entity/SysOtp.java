package com.study8.mini.sys.entity;

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

import java.time.LocalDateTime;

/**
 * SysOtp
 * @Date: 2024-11-26
 * @Author: HuyNH
 * @Desc: SysOtp
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sys_otp")
public class SysOtp extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "sent_date")
    private LocalDateTime sendDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "verified_date")
    private LocalDateTime verifiedDate;
}
