package com.nicefish.rbac.service;

import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INiceFishSessionService {
    NiceFishSessionEntity findDistinctBySessionId(String sessionId);

    Page<NiceFishSessionEntity> findNiceFishSessionEntitiesByUserId(Integer userId, Pageable pageable);

    int deleteDistinctBySessionId(String sessionId);

    NiceFishSessionEntity saveSession(NiceFishSessionEntity session);
}
