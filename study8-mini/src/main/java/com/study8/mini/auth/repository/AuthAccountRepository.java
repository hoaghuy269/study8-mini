package com.study8.mini.auth.repository;

import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

/**
 * AuthAccountRepository
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountRepository
 */
@Repository
public interface AuthAccountRepository
        extends CommonRepository<AuthAccount, AuthAccountDto> {
}
