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
    Page<ComponentPermissionEntity> getComponentPermissionTree(ComponentPermissionEntity componentPermissionEntity, Pageable pageable);

    Iterable<ComponentPermissionEntity> getPermListAllByRole(RoleEntity roleEntity);

    ComponentPermissionEntity getComponentPermissionDetail(Integer compPermId);

    AjaxResult createComponentPermission(ComponentPermissionEntity componentPermissionEntity);

    AjaxResult updateComponentPermission(ComponentPermissionEntity componentPermissionEntity);

    AjaxResult deleteComponentPermission(Integer compPermId);

    AjaxResult getComponentPermissionByUserId(Integer userId);
}
