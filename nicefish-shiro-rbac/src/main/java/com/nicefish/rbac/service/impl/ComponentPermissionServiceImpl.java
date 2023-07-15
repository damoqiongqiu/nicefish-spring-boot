package com.nicefish.rbac.service.impl;


import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.repository.IComponentPermissionRepository;
import com.nicefish.rbac.service.IComponentPermissionService;
import org.springframework.beans.BeanUtils;
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
    private IComponentPermissionRepository componentPermissionRepository;

    /**
     * 此方法从根节点开始，包含所有层级上的子节点，带分页
     */
    @Override
    public Page<ComponentPermissionEntity> getComponentPermissionTree(ComponentPermissionEntity componentPermissionEntity,Pageable pageable) {
        Page<ComponentPermissionEntity> page= this.componentPermissionRepository.findAll(new Specification<ComponentPermissionEntity>() {
            @Override
            public Predicate toPredicate(Root<ComponentPermissionEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(criteriaBuilder.isNull(root.get("parentEntity")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
        return page;
    }

    @Override
    public ComponentPermissionEntity getComponentPermissionDetail(Integer compPermId) {
        return this.componentPermissionRepository.findDistinctByCompPermId(compPermId);
    }

    @Override
    public AjaxResult createComponentPermission(ComponentPermissionEntity componentPermissionEntity) {
        componentPermissionEntity=this.componentPermissionRepository.save(componentPermissionEntity);
        return AjaxResult.success(componentPermissionEntity);
    }

    @Override
    public AjaxResult deleteComponentPermission(Integer compPermId) {
        this.componentPermissionRepository.deleteById(compPermId);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult editComponentPermission(ComponentPermissionEntity componentPermissionEntity) {
        //TODO:数据校验
        ComponentPermissionEntity oldEntity=this.componentPermissionRepository.findDistinctByCompPermId(componentPermissionEntity.getCompPermId());
        BeanUtils.copyProperties(componentPermissionEntity,oldEntity);
        this.componentPermissionRepository.save(oldEntity);
        return AjaxResult.success("保存成功");
    }

    @Override
    public boolean isPermissionStrUnique(String permissionStr) {
        return false;
    }

    @Override
    public Page<RoleEntity> getRoleListByComponentPermissionId(Integer compPermId, Pageable pageable) {
        return null;
    }
}
