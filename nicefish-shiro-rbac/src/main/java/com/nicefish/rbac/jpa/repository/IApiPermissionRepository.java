package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IApiPermissionRepository extends PagingAndSortingRepository<ApiPermissionEntity, Integer>, JpaSpecificationExecutor {
    ApiPermissionEntity findDistinctByApiPermissionId(Integer apiPermissionId);

    Iterable<ApiPermissionEntity> findAllByRoleEntitiesIn(List<RoleEntity> roleEntityList);

    @Transactional
    int deleteByApiPermissionId(Integer apiPermissionId);
}
