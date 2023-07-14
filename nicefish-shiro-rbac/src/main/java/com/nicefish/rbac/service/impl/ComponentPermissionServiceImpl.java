package com.nicefish.rbac.service.impl;


import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.repository.IComponentRepository;
import com.nicefish.rbac.service.IComponentPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Service
public class ComponentPermissionServiceImpl implements IComponentPermissionService {
    @Autowired
    private IComponentRepository componentRepository;

    /**
     * 此方法从根节点开始，包含所有层级上的子节点，带分页
     */
    @Override
    public Page<ComponentPermissionEntity> getComponentTree(ComponentPermissionEntity componentPermissionEntity,Pageable pageable) {
        Page<ComponentPermissionEntity> page= this.componentRepository.findAll(new Specification<ComponentPermissionEntity>() {
            @Override
            public Predicate toPredicate(Root<ComponentPermissionEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(criteriaBuilder.isNull(root.get("parentEntity")));
//                criteriaBuilder.asc(root.get("displayOrder"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
        return page;
    }

    @Override
    public ComponentPermissionEntity getComponentDetail(Integer compPermId) {
        return null;
    }

    @Override
    public Iterable<ComponentPermissionEntity> getAllComponentsNotIn(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public AjaxResult createComponent(ComponentPermissionEntity componentPermissionEntity) {
        return null;
    }

    @Override
    public AjaxResult deleteComponent(ComponentPermissionEntity componentPermissionEntity) {
        return null;
    }

    @Override
    public int deleteComponent(Integer compPermId) {
        return 0;
    }

    @Override
    public AjaxResult editComponent(ComponentPermissionEntity componentPermissionEntity) {
        return null;
    }

    @Override
    public boolean isPermissionStrUnique(String permissionStr) {
        return false;
    }

    @Override
    public Page<RoleEntity> getRoleListByComponentId(Integer compPermId, Pageable pageable) {
        return null;
    }
}
