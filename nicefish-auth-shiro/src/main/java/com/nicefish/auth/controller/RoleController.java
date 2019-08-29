package com.nicefish.auth.controller;

import com.nicefish.auth.jpa.entity.PermissionEntity;
import com.nicefish.auth.jpa.entity.RoleEntity;
import com.nicefish.auth.service.IRoleService;
import com.nicefish.auth.service.IUserService;
import com.nicefish.core.web.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * @author 大漠穷秋
 */
@Api("Role Management")
@RestController
@RequestMapping("/nicefish/auth/role")
public class RoleController {

    @Autowired
    protected IRoleService roleService2;

    @Autowired
    protected IUserService userService2;

    @RequestMapping(value = "/list2/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<RoleEntity> getRoleList(@RequestBody RoleEntity roleEntity, @PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<RoleEntity> roleList = roleService2.getRoleListPaging(roleEntity,pageable);
        return roleList;
    }

    @RequestMapping(value = "/create2",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createRole(@RequestBody RoleEntity roleEntity){
        return this.roleService2.createRole(roleEntity);
    }

    @RequestMapping(value = "/delete2/{roleId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteRole(@PathVariable(value="roleId",required = true)Long roleId){
        int affected= this.roleService2.deleteRole(roleId);
        if(affected==0){
            return AjaxResult.failure("删除失败，系统内置角色或者正在使用中。");
        }else{
            return AjaxResult.success("删除成功");
        }
    }

    @RequestMapping(value = "/edit2",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editRole(@RequestBody RoleEntity roleEntity){
        return this.roleService2.editRole(roleEntity);
    }

    @RequestMapping(value = "/detail2/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public RoleEntity getRoleDetail(@PathVariable(value = "roleId",required = true) Long roleId){
        return this.roleService2.getRoleById(roleId);
    }

    @RequestMapping(value = "/get-all-permissions/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public Iterable<PermissionEntity> getAllPermissions(@PathVariable(value="roleId",required = true) Long roleId) {
        return this.roleService2.getAllPermissionsByRoleId(roleId);
    }

    @RequestMapping(value = "/add-auth-users/{roleId}/{userIds}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addAuthUsers(
            @PathVariable(value = "roleId",required = true) Long roleId,
            @PathVariable(value = "userIds",required = true) Long[] userIds
    ){
        return this.roleService2.addAuthUsers(roleId,userIds);
    }

    @RequestMapping(value = "/del-auth-users/{roleId}/{userIds}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delAuthUsers(
            @PathVariable(value = "roleId",required = true) Long roleId,
            @PathVariable(value = "userIds",required = true) Long[] userIds
    ){
        return this.roleService2.deleteAuthUsers(roleId,userIds);
    }
}
