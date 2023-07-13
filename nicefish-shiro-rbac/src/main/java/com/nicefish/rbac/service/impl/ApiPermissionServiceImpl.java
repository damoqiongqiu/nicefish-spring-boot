package com.nicefish.rbac.service.impl;


import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiEntity;
import com.nicefish.rbac.jpa.repository.IApiPermissionRepository;
import com.nicefish.rbac.service.IApiPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Service
public class ApiPermissionServiceImpl implements IApiPermissionService {
    @Autowired
    private IApiPermissionRepository apiPermRepository;

    @Override
    public Page<ApiEntity> getPermListPaging(ApiEntity permEntity, Pageable pageable) {
        return this.apiPermRepository.findAll(new Specification<ApiEntity>() {
            @Override
            public Predicate toPredicate(Root<ApiEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(!StringUtils.isEmpty(permEntity.getApiName())){
                    predicates.add(criteriaBuilder.like(root.get("apiName"),"%"+permEntity.getApiName()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public AjaxResult createApiPermission(ApiEntity permEntity) {
        permEntity=this.apiPermRepository.save(permEntity);
        return new AjaxResult(true,permEntity);
    }

    @Override
    public AjaxResult editPermission(ApiEntity permEntity) {
        //TODO:数据校验
        ApiEntity permEntityDB=this.apiPermRepository.findDistinctByApiId(permEntity.getApiId());
        BeanUtils.copyProperties(permEntity,permEntityDB);
        this.apiPermRepository.save(permEntity);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    public ApiEntity getApiPermissionById(Integer apiId) {
        return this.apiPermRepository.findDistinctByApiId(apiId);
    }

    @Override
    @Transactional
    public int deleteByApiId(Integer apiId) {
        return this.apiPermRepository.deleteByApiId(apiId);
    }
}
