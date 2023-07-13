package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.RoleApiEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IRoleApiRepository extends PagingAndSortingRepository<RoleApiEntity, Integer>, JpaSpecificationExecutor {
    @Transactional
    int deleteByRoleIdAndApiId(Integer roleId,Integer apiId);

    @Transactional
    int deleteByRoleIdAndApiIdIsIn(Integer roleId,Integer[] apiIds);

    int deleteAllByRoleId(Integer roleId);

    int deleteByApiId(Integer apiId);

    Iterable<RoleApiEntity> findAllByRoleId(Integer roleId);//TODO:分页？

    Iterable<RoleApiEntity> findAllByApiId(Integer apiId);//TODO:分页？
}
