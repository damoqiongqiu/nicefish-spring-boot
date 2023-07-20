package com.nicefish.rbac.service;

import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 大漠穷秋
 */
public interface IApiPermissionService {
    Page<ApiPermissionEntity> getPermListPaging(ApiPermissionEntity apiPermissionEntity, Pageable pageable);
    Iterable<ApiPermissionEntity> getPermListAll(ApiPermissionEntity apiPermissionEntity);
    Iterable<ApiPermissionEntity> getPermListAllByRole(RoleEntity roleEntity);
    ApiPermissionEntity createApiPermission(ApiPermissionEntity apiPermissionEntity);
    ApiPermissionEntity updatePermission(ApiPermissionEntity apiPermissionEntity);
    ApiPermissionEntity getApiPermissionById(Integer apiPermissionEntity);
    int deleteByApiId(Integer apiPermissionId);
}
