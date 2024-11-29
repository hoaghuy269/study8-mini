package com.study8.mini.auth.repository;

import com.study8.mini.auth.dto.AuthAccountRoleDto;
import com.study8.mini.auth.entity.AuthAccountRole;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * AuthAccountRoleRepository
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthAccountRoleRepository
 */
@Repository
public interface AuthAccountRoleRepository
        extends CommonRepository<AuthAccountRole, AuthAccountRoleDto> {
    @Query("select e from AuthAccountRole e "
            + "where e.accountId = :accountId "
            + "and coalesce(e.deletedId, 0) = 0")
    Optional<List<AuthAccountRole>> findByAccountId(@Param("accountId") Long accountId);
}
