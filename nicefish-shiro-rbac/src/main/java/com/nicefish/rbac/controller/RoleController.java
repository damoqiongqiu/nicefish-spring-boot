package com.nicefish.rbac.controller;

import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.service.IRoleService;
import com.nicefish.rbac.service.IUserService;
import com.nicefish.core.utils.AjaxResult;
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
    protected IRoleService roleService;

    @Autowired
    protected IUserService userService;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<RoleEntity> getRoleList(@RequestBody RoleEntity roleEntity, @PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<RoleEntity> roleList = roleService.getRoleListPaging(roleEntity,pageable);
        return roleList;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createRole(@RequestBody RoleEntity roleEntity){
        return this.roleService.createRole(roleEntity);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editRole(@RequestBody RoleEntity roleEntity){
        return this.roleService.editRole(roleEntity);
    }

    @RequestMapping(value = "/delete/{roleId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteRole(@PathVariable(value="roleId",required = true)Integer roleId){
        int affected= this.roleService.deleteRole(roleId);
        if(affected==0){
            return AjaxResult.failure("删除失败，系统内置角色或者正在使用中。");
        }else{
            return AjaxResult.success("删除成功");
        }
    }
    
    @RequestMapping(value = "/detail/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public RoleEntity getRoleDetail(@PathVariable(value = "roleId",required = true) Integer roleId){
        return this.roleService.getRoleById(roleId);
    }

    // @RequestMapping(value = "/get-all-permissions/{roleId}",method = RequestMethod.GET)
    // @ResponseBody
    // public Iterable<PermissionEntity> getAllPermissions(@PathVariable(value="roleId",required = true) Integer roleId) {
    //     return this.roleService.getAllPermissionsByRoleId(roleId);
    // }

    @RequestMapping(value = "/add-auth-users/{roleId}/{userIds}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addAuthUsers(
            @PathVariable(value = "roleId",required = true) Integer roleId,
            @PathVariable(value = "userIds",required = true) Integer[] userIds
    ){
        return this.roleService.addAuthUsers(roleId,userIds);
    }

    @RequestMapping(value = "/del-auth-users/{roleId}/{userIds}",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult delAuthUsers(
            @PathVariable(value = "roleId",required = true) Integer roleId,
            @PathVariable(value = "userIds",required = true) Integer[] userIds
    ){
        return this.roleService.deleteAuthUsers(roleId,userIds);
    }
}
