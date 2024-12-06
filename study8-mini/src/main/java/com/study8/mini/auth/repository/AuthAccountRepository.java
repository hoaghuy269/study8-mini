package com.study8.mini.auth.repository;

import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.auth.entity.AuthAccount;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AuthAccountRepository
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountRepository
 */
@Repository
@EnableJpaRepositories
public interface AuthAccountRepository
        extends CommonRepository<AuthAccount, AuthAccountDto> {
    @Query("select e from AuthAccount e "
            + "where e.username = :username "
            + "and coalesce(e.deletedId, 0) = 0")
    Optional<AuthAccount> findByUsername(@Param("username") String username);

    @Query("select e from AuthAccount e "
            + "where e.email = :email "
            + "and coalesce(e.deletedId, 0) = 0")
    Optional<AuthAccount> findByEmail(@Param("email") String email);
}
