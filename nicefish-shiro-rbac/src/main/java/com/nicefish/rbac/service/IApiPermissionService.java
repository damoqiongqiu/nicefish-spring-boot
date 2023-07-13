package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 大漠穷秋
 */
public interface IApiPermissionService {
    Page<ApiPermissionEntity> getPermListPaging(ApiPermissionEntity permEntity, Pageable pageable);
    AjaxResult createApiPermission(ApiPermissionEntity permEntity);
    AjaxResult editPermission(ApiPermissionEntity permEntity);
    ApiPermissionEntity getApiPermissionById(Integer permissionId);
    int deleteByApiId(Integer apiPermissionId);
}
