package com.nicefish.rbac.service.impl;


import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.service.IApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author 大漠穷秋
 */
@Service
public class ApiServiceImpl implements IApiService {

    @Override
    public Page<ApiEntity> getApiList(ApiEntity apiEntity, Pageable pageable) {
        return null;
    }

    @Override
    public ApiEntity getApiDetail(Integer apiId) {
        return null;
    }

    @Override
    public Iterable<ApiEntity> getAllApisNotIn(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public AjaxResult createApi(ApiEntity apiEntity) {
        return null;
    }

    @Override
    public AjaxResult deleteApi(ApiEntity apiEntity) {
        return null;
    }

    @Override
    public int deleteApi(Integer apiId) {
        return 0;
    }

    @Override
    public AjaxResult editApi(ApiEntity apiEntity) {
        return null;
    }

    @Override
    public boolean isPermissionStrUnique(String permissionStr) {
        return false;
    }

    @Override
    public Page<RoleEntity> getRoleListByApiId(Integer apiId, Pageable pageable) {
        return null;
    }
}
