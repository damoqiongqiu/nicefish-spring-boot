package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IApiPermissionRepository extends PagingAndSortingRepository<ApiPermissionEntity, Integer>, JpaSpecificationExecutor {
    ApiPermissionEntity findDistinctByApiPermissionId(Integer apiPermissionId);
    
    @Transactional
    int deleteByApiPermissionId(Integer apiPermissionId);
}
