package com.study8.mini.auth.repository;

import com.study8.mini.auth.dto.AuthRoleDto;
import com.study8.mini.auth.entity.AuthRole;
import com.study8.mini.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthRoleRepository
        extends CommonRepository<AuthRole, AuthRoleDto> {
    @Query(value = """
            SELECT 
                ar.id AS id,
                ar.name AS name,
                ar.created_date AS created_date,
                ar.created_id AS created_id,
                ar.updated_date AS updated_date,
                ar.updated_id AS updated_id,
                ar.deleted_date AS deleted_date,
                ar.deleted_id AS deleted_id
            FROM auth_account_role aar
            LEFT JOIN auth_role ar 
                ON aar.role_id = ar.id 
                AND COALESCE(ar.deleted_id, 0) = 0
            WHERE COALESCE(aar.deleted_id, 0) = 0
              AND aar.account_id = :accountId
            """,
            nativeQuery = true)
    Optional<List<AuthRole>> findByAccountId(@Param("accountId") Long accountId);
}
