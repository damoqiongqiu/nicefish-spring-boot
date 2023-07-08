package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRolePermissionRepository extends PagingAndSortingRepository<RolePermissionEntity, Long>, JpaSpecificationExecutor {
    int deleteByRoleIdAndPermissionIdIsIn(Long roleId,Long[] permissionIds);
    int deleteAllByRoleId(Long roleId);
    int deleteByRoleIdAndPermissionId(Long roleId,Long permissionId);
    Iterable<RolePermissionEntity> findAllByRoleId(Long roleId);
}
