package com.nicefish.rbac.jpa.repository;

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
public interface IRoleComponentRepository extends PagingAndSortingRepository<RoleComponentEntity, Integer>, JpaSpecificationExecutor {
    int deleteByRoleIdAndComponentId(Integer roleId,Integer componentId);
    int deleteByRoleIdAndComponentIdIsIn(Integer roleId,Integer[] componentIds);
    int deleteAllByRoleId(Integer roleId);
    int deleteByComponentId(Integer componentId);
    Iterable<RoleComponentEntity> findAllByRoleId(Integer roleId);//TODO:分页？
    Iterable<RoleComponentEntity> findAllByComponentId(Integer componentId);//TODO:分页？
}
