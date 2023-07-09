package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ComponentEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author 大漠穷秋
 */
public interface IComponentService {

    Page<ComponentEntity> getComponentList(ComponentEntity componentEntity, Pageable pageable);

    ComponentEntity getComponentDetail(Integer componentId);

    Iterable<ComponentEntity> getAllComponentsNotIn(RoleEntity roleEntity);

    AjaxResult createComponent(ComponentEntity componentEntity);

    AjaxResult deleteComponent(ComponentEntity componentEntity);

    int deleteComponent(Integer componentId);

    AjaxResult editComponent(ComponentEntity componentEntity);

    //权限字符串是否唯一
    boolean isPermissionStrUnique(String permissionStr);

    Page<RoleEntity> getRoleListByComponentId(Integer componentId, Pageable pageable);
}
