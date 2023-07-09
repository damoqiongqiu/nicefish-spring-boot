package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RoleApiEntity;
import com.nicefish.rbac.jpa.entity.RoleComponentEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRoleApiRepository extends PagingAndSortingRepository<RoleApiEntity, Integer>, JpaSpecificationExecutor {
    int deleteByRoleIdAndApiId(Integer roleId,Integer apiId);
    int deleteByRoleIdAndApiIdIsIn(Integer roleId,Integer[] apiIds);
    int deleteAllByRoleId(Integer roleId);
    int deleteByApiId(Integer apiId);
    Iterable<RoleApiEntity> findAllByRoleId(Integer roleId);//TODO:分页？
    Iterable<RoleApiEntity> findAllByApiId(Integer apiId);//TODO:分页？
}
