package com.nicefish.rbac.controller;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiPermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.service.IApiPermissionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 服务端 API 权限管理
 * @author 大漠穷秋
 */
@Api("Api Permission Controller")
@RestController
@RequestMapping("/nicefish/auth/api-permission")
public class ApiPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(ApiPermissionController.class);

    @Autowired
    protected IApiPermissionService apiPermService;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<ApiPermissionEntity> getPermissionList(@RequestBody ApiPermissionEntity apiPermissionEntity, @PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<ApiPermissionEntity> permList = apiPermService.getPermListPaging(apiPermissionEntity,pageable);
        return permList;
    }

    @RequestMapping(value = "/list-all",method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApiPermissionEntity> getPermissionListAll(@RequestBody ApiPermissionEntity apiPermissionEntity) {
        return apiPermService.getPermListAll(apiPermissionEntity);
    }

    @RequestMapping(value = "/list-all-by-role",method = RequestMethod.POST)
    @ResponseBody
    public Iterable<ApiPermissionEntity> getPermissionListByRole(@RequestBody RoleEntity roleEntity) {
        return apiPermService.getPermListAllByRole(roleEntity);
    }

    @RequestMapping(value = "/detail/{apiPermissionId}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getApiPermissionDetail(@PathVariable(value = "apiPermissionId",required = true) Integer apiPermissionId){
        return AjaxResult.success(this.apiPermService.getApiPermissionById(apiPermissionId));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createApiPermission(@RequestBody ApiPermissionEntity apiPermissionEntity){
        return this.apiPermService.createApiPermission(apiPermissionEntity);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editPermission(@RequestBody ApiPermissionEntity apiPermissionEntity){
        return this.apiPermService.editPermission(apiPermissionEntity);
    }

    @RequestMapping(value = "/delete/{apiPermissionId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteByApiId(@PathVariable(value="apiPermissionId",required = true)Integer apiPermissionId){
        //TODO:数据校验，数据关联性测试
        this.apiPermService.deleteByApiId(apiPermissionId);
        return AjaxResult.success();
    }
}
