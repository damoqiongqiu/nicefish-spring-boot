package com.nicefish.rbac.service;

import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.core.utils.AjaxResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 大漠穷秋
 */
public interface IRoleService {
    AjaxResult createRole(RoleEntity roleEntity);

    int deleteRole(Integer roleId);

    AjaxResult deleteRoleByIds(String ids);

    AjaxResult editRole(RoleEntity roleEntity);

    Page<RoleEntity> getRoleListPaging(RoleEntity roleEntity, Pageable pageable);

    RoleEntity getRoleById(Integer roleId);

    Page<UserEntity> getAuthUsersByRoleId(Integer roleId,Pageable pageable);

    AjaxResult deleteAuthUsers(RoleEntity roleEntity);

    AjaxResult deleteAuthUsers(Integer roleId);

    AjaxResult deleteAuthUsers(Integer roleId, Integer[] userIds);

    AjaxResult addAuthUsers(Integer roleId, Integer[] userIds);

    AjaxResult addAuthUsers(Integer roleId, List<UserEntity> userEntities);

    boolean isRoleNameUnique(String roleName);

    //根据 roleId 获取此角色的所有权限字符串
    Iterable<String> getPermStrsByRoleId(Integer roleId);

    AjaxResult addComponentPermission(RoleEntity roleEntity, ComponentPermissionEntity componentPermissionEntity);

    AjaxResult addComponentPermissions(RoleEntity roleEntity, List<ComponentPermissionEntity> componentEntities);
    
    AjaxResult addApiPermission(RoleEntity roleEntity, ApiPermissionEntity apiPermissionEntity);

    AjaxResult deleteAllPermissionsByRoleId(Integer roleId);
    
    AjaxResult deleteComponentPermission(RoleEntity roleEntity, ComponentPermissionEntity componentPermissionEntity);

    AjaxResult deleteComponentPermissions(Integer roleId, Integer[] componentIds);

    AjaxResult deleteApiPermission(RoleEntity roleEntity, ApiPermissionEntity apiPermissionEntity);

    AjaxResult deleteApiPermissions(Integer roleId, Integer[] componentIds);
}
