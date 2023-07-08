package com.nicefish.rbac.controller;

import com.nicefish.rbac.jpa.entity.PermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.service.IPermissionService;
import com.nicefish.core.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 权限代码管理
 * @author 大漠穷秋
 */
@Api("Permission Management")
@RestController
@RequestMapping("/nicefish/auth/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation("获取权限列表，带分页")
    @RequestMapping(value = "/list2/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<PermissionEntity> getPermissionList(@RequestBody PermissionEntity permissionEntity, @PathVariable(name="page",required = true) Integer page){
        Pageable pageable= PageRequest.of(page-1,10);
        return this.permissionService.getPermissionList(permissionEntity,pageable);
    }

    @ApiOperation("获取权限详情")
    @RequestMapping(value = "/detail/{permissionId}",method = RequestMethod.GET)
    @ResponseBody
    public PermissionEntity getPermissionDetail(@PathVariable(value="permissionId",required = true)Long permissionId){
        return this.permissionService.getPermissionDetail(permissionId);
    }

    @ApiOperation("获取全部权限列表，排除指定的角色")
    @RequestMapping(value = "/all",method = RequestMethod.POST)
    @ResponseBody
    public Iterable<PermissionEntity> getAllPermissions(@RequestBody RoleEntity roleEntity){
        return this.permissionService.getAllPermissionsNotIn(roleEntity);
    }

    @ApiOperation("创建新权限点")
    @RequestMapping(value = "/create2",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createPermission(@RequestBody PermissionEntity permissionEntity){
        AjaxResult ajaxResult =this.permissionService.createPermission(permissionEntity);
        return ajaxResult;
    }

    @ApiOperation("编辑权限点")
    @RequestMapping(value = "/edit2",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editRole(@RequestBody PermissionEntity permissionEntity){
        return this.permissionService.editPermission(permissionEntity);
    }

    @ApiOperation("删除权限点")
    @RequestMapping(value = "/delete2/{permissionId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deletePermission(@PathVariable(value="permissionId",required = true)Long permissionId){
        int affected= this.permissionService.deletePermission(permissionId);
        if(affected==0){
            return new AjaxResult(false,"删除失败，系统内置权限或者正在使用中。");
        }else{
            return new AjaxResult(true,"删除成功");
        }
    }
}
