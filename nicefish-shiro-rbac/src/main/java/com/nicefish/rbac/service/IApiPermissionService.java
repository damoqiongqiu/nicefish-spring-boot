package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 大漠穷秋
 */
public interface IApiPermissionService {
    Page<ApiEntity> getPermListPaging(ApiEntity permEntity, Pageable pageable);
    AjaxResult createApiPermission(ApiEntity permEntity);
    AjaxResult editPermission(ApiEntity permEntity);
    AjaxResult getApiPermissionById(Integer permissionId);
    int deleteByApiId(Integer apiId);
}
