package com.nicefish.rbac.service.impl;

import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;
import com.nicefish.rbac.jpa.repository.INiceFishSessionRepository;
import com.nicefish.rbac.service.INiceFishSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Service
public class NiceFishSessionServiceImpl implements INiceFishSessionService {
    private static final Logger logger = LoggerFactory.getLogger(NiceFishSessionServiceImpl.class);

    @Autowired
    private INiceFishSessionRepository sessionRepository;

    @Override
    public NiceFishSessionEntity findDistinctBySessionId(String sessionId) {
        return this.sessionRepository.findDistinctBySessionId(sessionId);
    }

    @Override
    public Page<NiceFishSessionEntity> findNiceFishSessionEntitiesByUserId(Integer userId, Pageable pageable) {
        return this.sessionRepository.findAll(new Specification<NiceFishSessionEntity>() {
            @Override
            public Predicate toPredicate(Root<NiceFishSessionEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("userId"),userId));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public int deleteDistinctBySessionId(String sessionId) {
        return this.sessionRepository.deleteDistinctBySessionId(sessionId);
    }

    @Override
    public NiceFishSessionEntity saveSession(NiceFishSessionEntity session) {
        return this.sessionRepository.save(session);
    }
}
