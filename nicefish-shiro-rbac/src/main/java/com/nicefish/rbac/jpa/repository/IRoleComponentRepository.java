package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RoleComponentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRoleComponentRepository extends PagingAndSortingRepository<RoleComponentEntity, Integer>, JpaSpecificationExecutor {
    int deleteByRoleIdAndCompPermId(Integer roleId,Integer compPermId);
    int deleteByRoleIdAndCompPermIdIsIn(Integer roleId,Integer[] componentIds);
}
