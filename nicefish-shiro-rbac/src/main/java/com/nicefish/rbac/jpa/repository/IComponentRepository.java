package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.ComponentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IComponentRepository extends PagingAndSortingRepository<ComponentEntity, Integer>, JpaSpecificationExecutor {
    ComponentEntity findDistinctByComponentId(Integer componentId);
    
    int deleteByComponentId(Integer componentId);
}
