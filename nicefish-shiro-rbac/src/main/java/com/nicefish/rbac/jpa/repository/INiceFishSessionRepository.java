package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;
import com.nicefish.rbac.jpa.entity.PermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface INiceFishSessionRepository extends PagingAndSortingRepository<NiceFishSessionEntity, Long>, JpaSpecificationExecutor {
    NiceFishSessionEntity findDistinctBySessionId(String sessionId);
    
    Page<NiceFishSessionEntity> findNiceFishSessionEntitiesByUserId(Long userId, Pageable pageable);
    
    int deleteDistinctBySessionId(String sessionId);
}
