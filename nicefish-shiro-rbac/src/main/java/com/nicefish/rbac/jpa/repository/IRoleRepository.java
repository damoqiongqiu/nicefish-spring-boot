package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRoleRepository extends PagingAndSortingRepository<RoleEntity, Integer>, JpaSpecificationExecutor {
    Iterable<RoleEntity> findAllByRoleId(Integer roleId);

    Page<UserEntity> findUserEntitiesByRoleId(Integer roleId, Pageable pageable);

    RoleEntity findDistinctByRoleName(String roleName);

    RoleEntity findDistinctByRoleId(Integer roleId);

    @Transactional
    void deleteAllByRoleIdIn(Integer[] roleIds);

    @Transactional
    int deleteByRoleId(Integer roleId);
}
