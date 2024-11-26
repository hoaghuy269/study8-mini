package com.study8.mini.common.repository;

import com.study8.mini.common.dto.CommonDto;
import com.study8.mini.common.entity.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * CommonRepository
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: CommonRepository
 */
@NoRepositoryBean
public interface CommonRepository<E extends CommonEntity, D extends CommonDto>
        extends JpaRepository<E, Long> {
    @Query("SELECT e FROM #{#entityName} e "
            + "WHERE e.id = :id "
            + "and coalesce(e.deletedId, 0) = 0")
    Optional<E> findData(@Param("id") Long id);
}
