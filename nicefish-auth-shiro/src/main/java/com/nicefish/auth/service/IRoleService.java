package com.nicefish.auth.service;

import com.nicefish.auth.jpa.entity.PermissionEntity;
import com.nicefish.auth.jpa.entity.RoleEntity;
import com.nicefish.auth.jpa.entity.UserEntity;
import com.nicefish.core.web.AjaxResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 大漠穷秋
 */
public interface IRoleService {
    AjaxResult createRole(RoleEntity roleEntity);

    int deleteRole(Long roleId);

    AjaxResult deleteRoleByIds(String ids);

    AjaxResult editRole(RoleEntity roleEntity);

    Page<RoleEntity> getRoleListPaging(RoleEntity roleEntity, Pageable pageable);

    RoleEntity getRoleById(Long roleId);

    Page<UserEntity> getAuthUsersByRoleId(Long roleId,Pageable pageable);

    AjaxResult deleteAuthUsers(RoleEntity roleEntity);

    AjaxResult deleteAuthUsers(Long roleId);

    AjaxResult deleteAuthUsers(Long roleId, Long[] userIds);

    AjaxResult addAuthUsers(Long roleId, Long[] userIds);

    AjaxResult addAuthUsers(Long roleId, List<UserEntity> userEntities);

    boolean isRoleKeyUnique(String roleKey);

    boolean isRoleNameUnique(String roleName);

    Iterable<PermissionEntity> getAllPermissionsByRoleId(Long roleId);

    AjaxResult addPermissions(Long roleId, Long[] permissionIds);

    AjaxResult addPermissions(RoleEntity roleEntity, List<PermissionEntity> permissionEntities);

    AjaxResult deletePermissions(Long roleId, Long[] permissionIds);

    AjaxResult deleteAllPermissionsByRoleId(Long roleId);

    AjaxResult deletePermission(Long roleId, Long permissionId);
}
