package com.nicefish.rbac.service;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 大漠穷秋
 */
public interface IRoleService {
    AjaxResult createRole(RoleEntity roleEntity);

    int deleteRole(Integer roleId);

    AjaxResult updateRole(RoleEntity newRoleEntity);

    Page<RoleEntity> getRoleListPaging(RoleEntity roleEntity, Pageable pageable);

    RoleEntity getRoleById(Integer roleId);

    AjaxResult deleteAuthUsers(Integer roleId, Integer[] userIds);

    AjaxResult addAuthUsers(Integer roleId, Integer[] userIds);
}
