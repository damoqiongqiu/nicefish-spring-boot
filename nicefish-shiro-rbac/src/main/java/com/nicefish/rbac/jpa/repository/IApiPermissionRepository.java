package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 大漠穷秋
 */
public interface IApiPermissionRepository extends PagingAndSortingRepository<ApiPermissionEntity, Integer>, JpaSpecificationExecutor, JpaRepository<ApiPermissionEntity, Integer> {
    ApiPermissionEntity findDistinctByApiPermissionId(Integer apiPermissionId);

    Iterable<ApiPermissionEntity> findAllByRoleEntitiesIn(List<RoleEntity> roleEntityList);

    @Transactional
    int deleteByApiPermissionId(Integer apiPermissionId);
}
