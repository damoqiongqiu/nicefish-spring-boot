package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 大漠穷秋
 */
public interface INiceFishSessionRepository extends PagingAndSortingRepository<NiceFishSessionEntity, Integer>, JpaSpecificationExecutor, JpaRepository<NiceFishSessionEntity, Integer> {
    NiceFishSessionEntity findDistinctBySessionId(String sessionId);
    
    Page<NiceFishSessionEntity> findNiceFishSessionEntitiesByUserId(Integer userId, Pageable pageable);

    @Transactional
    int deleteDistinctBySessionId(String sessionId);
}
