package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ApiEntity;
import com.nicefish.rbac.jpa.entity.ComponentEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IApiRepository extends PagingAndSortingRepository<ApiEntity, Integer>, JpaSpecificationExecutor {
    Page<ApiEntity> findByPermission(String permission, Pageable pageable);

    int deleteByApiId(Integer apiId);
}
