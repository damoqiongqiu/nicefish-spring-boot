package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.PermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IPermissionRepository extends PagingAndSortingRepository<PermissionEntity, Long>, JpaSpecificationExecutor {
    PermissionEntity findDistinctByPermissionStr(String permissionStr);

    PermissionEntity findDistinctByPermissionId(Long permissionId);

    Page<RoleEntity> findRoleEntitiesByPermissionId(Long permissionId, Pageable pageable);

    int deleteDistinctByPermissionId(Long permissionId);

    Iterable<PermissionEntity> findPermissionEntitiesByRoleEntitiesIn(Iterable<RoleEntity> roleEntities);

    Iterable<PermissionEntity> findPermissionEntitiesByPermissionIdNotIn(Long[] permissionIds);
}
