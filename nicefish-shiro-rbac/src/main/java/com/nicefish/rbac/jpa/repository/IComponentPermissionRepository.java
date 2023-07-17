package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IComponentPermissionRepository extends PagingAndSortingRepository<ComponentPermissionEntity, Integer>, JpaSpecificationExecutor {
    ComponentPermissionEntity findDistinctByCompPermId(Integer compPermId);

    Iterable<ComponentPermissionEntity> findAllByRoleEntitiesIn(List<RoleEntity> roleEntityList);
}
