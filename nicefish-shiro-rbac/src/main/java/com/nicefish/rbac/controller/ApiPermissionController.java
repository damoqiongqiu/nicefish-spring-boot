package com.nicefish.rbac.controller;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.ApiEntity;
import com.nicefish.rbac.service.IApiPermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * API 权限管理
 * @author 大漠穷秋
 */
@Api("Api Permission Management")
@RestController
@RequestMapping("/nicefish/auth/api-permission")
public class ApiPermissionController {

    @Autowired
    protected IApiPermissionService apiPermService;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<ApiEntity> getPermissionList(@RequestBody ApiEntity apiEntity, @PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<ApiEntity> permList = apiPermService.getPermListPaging(apiEntity,pageable);
        return permList;
    }

    @RequestMapping(value = "/detail/{apiId}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getApiPermissionDetail(@PathVariable(value = "apiId",required = true) Integer apiId){
        return this.apiPermService.getApiPermissionById(apiId);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult createApiPermission(@RequestBody ApiEntity apiEntity){
        return this.apiPermService.createApiPermission(apiEntity);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editPermission(@RequestBody ApiEntity apiEntity){
        return this.apiPermService.editPermission(apiEntity);
    }

    @RequestMapping(value = "/delete/{apiId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteByApiId(@PathVariable(value="apiId",required = true)Integer apiId){
        //TODO:数据校验，数据关联性测试
        this.apiPermService.deleteByApiId(apiId);
        return AjaxResult.success();
    }
}
