package com.nicefish.rbac.service;

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

    ComponentPermissionEntity createComponentPermission(ComponentPermissionEntity componentPermissionEntity);

    ComponentPermissionEntity updateComponentPermission(ComponentPermissionEntity componentPermissionEntity);

    int deleteComponentPermission(Integer compPermId);

    Iterable<ComponentPermissionEntity> getComponentPermissionsByUserId(Integer userId);
}
