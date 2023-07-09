package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 大漠穷秋
 */
public interface IApiService {
    Page<ApiEntity> getApiList(ApiEntity apiEntity, Pageable pageable);

    ApiEntity getApiDetail(Integer apiId);

    Iterable<ApiEntity> getAllApisNotIn(RoleEntity roleEntity);

    AjaxResult createApi(ApiEntity apiEntity);

    AjaxResult deleteApi(ApiEntity apiEntity);

    int deleteApi(Integer apiId);

    AjaxResult editApi(ApiEntity apiEntity);

    //权限字符串是否唯一
    boolean isPermissionStrUnique(String permissionStr);

    Page<RoleEntity> getRoleListByApiId(Integer apiId, Pageable pageable);
}
