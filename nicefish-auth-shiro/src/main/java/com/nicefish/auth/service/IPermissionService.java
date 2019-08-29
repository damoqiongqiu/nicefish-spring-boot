package com.nicefish.auth.service;

import com.nicefish.auth.jpa.entity.PermissionEntity;
import com.nicefish.auth.jpa.entity.RoleEntity;
import com.nicefish.core.web.AjaxResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author 大漠穷秋
 */
public interface IPermissionService {
    PermissionEntity getPermissionDetail(Long permissionId);

    Iterable<PermissionEntity> getAllPermissionsNotIn(RoleEntity roleEntity);

    Iterable<PermissionEntity> getAllPermissions();

    Page<PermissionEntity> getPermissionList(PermissionEntity permissionEntity, Pageable pageable);

    AjaxResult createPermission(PermissionEntity permissionEntity);

    AjaxResult deletePermission(PermissionEntity permissionEntity);

    int deletePermission(Long permissionId);

    AjaxResult editPermission(PermissionEntity permissionEntity);

    boolean isPermissionStrUnique(PermissionEntity permissionEntity);

    Page<RoleEntity> getRoleListByPermissionId(Long permissionId, Pageable pageable);
}
