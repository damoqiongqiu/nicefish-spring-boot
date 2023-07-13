package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author 大漠穷秋
 */
public interface IComponentPermissionService {

    Page<ComponentPermissionEntity> getComponentList(Pageable pageable);

    ComponentPermissionEntity getComponentDetail(Integer compPermId);

    Iterable<ComponentPermissionEntity> getAllComponentsNotIn(RoleEntity roleEntity);

    AjaxResult createComponent(ComponentPermissionEntity componentPermissionEntity);

    AjaxResult deleteComponent(ComponentPermissionEntity componentPermissionEntity);

    int deleteComponent(Integer compPermId);

    AjaxResult editComponent(ComponentPermissionEntity componentPermissionEntity);

    //权限字符串是否唯一
    boolean isPermissionStrUnique(String permissionStr);

    Page<RoleEntity> getRoleListByComponentId(Integer compPermId, Pageable pageable);
}
