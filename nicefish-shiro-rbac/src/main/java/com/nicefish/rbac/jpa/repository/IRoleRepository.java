package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRoleRepository extends PagingAndSortingRepository<RoleEntity, Long>, JpaSpecificationExecutor {
    Iterable<RoleEntity> findAllByRoleId(Long roleId);

    Page<UserEntity> findUserEntitiesByRoleId(Long roleId, Pageable pageable);

    RoleEntity findDistinctByRoleKey(String roleKey);

    RoleEntity findDistinctByRoleName(String roleName);

    RoleEntity findDistinctByRoleId(Long roleId);

    void deleteAllByRoleIdIn(Long[] roleIds);

    int deleteByRoleId(Long roleId);
}
