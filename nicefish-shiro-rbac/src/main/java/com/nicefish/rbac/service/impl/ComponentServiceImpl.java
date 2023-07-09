package com.nicefish.rbac.service.impl;


import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ComponentEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.service.IComponentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author 大漠穷秋
 */
@Service
public class ComponentServiceImpl implements IComponentService {

    @Override
    public Page<ComponentEntity> getComponentList(ComponentEntity componentEntity, Pageable pageable) {
        return null;
    }

    @Override
    public ComponentEntity getComponentDetail(Integer componentId) {
        return null;
    }

    @Override
    public Iterable<ComponentEntity> getAllComponentsNotIn(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public AjaxResult createComponent(ComponentEntity componentEntity) {
        return null;
    }

    @Override
    public AjaxResult deleteComponent(ComponentEntity componentEntity) {
        return null;
    }

    @Override
    public int deleteComponent(Integer componentId) {
        return 0;
    }

    @Override
    public AjaxResult editComponent(ComponentEntity componentEntity) {
        return null;
    }

    @Override
    public boolean isPermissionStrUnique(String permissionStr) {
        return false;
    }

    @Override
    public Page<RoleEntity> getRoleListByComponentId(Integer componentId, Pageable pageable) {
        return null;
    }
}
