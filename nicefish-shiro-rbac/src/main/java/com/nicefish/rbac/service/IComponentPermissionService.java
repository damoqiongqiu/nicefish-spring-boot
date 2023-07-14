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
    /**
     * 此方法从根节点开始，包含所有层级上的子节点，带分页
     */
    Page<ComponentPermissionEntity> getComponentTree(ComponentPermissionEntity componentPermissionEntity, Pageable pageable);

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
