package com.nicefish.rbac.controller;

import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.service.IComponentPermissionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 前端页面组件权限管理
 * @author 大漠穷秋
 */
@Api("Component Permission Management")
@RestController
@RequestMapping("/nicefish/auth/api-permission")
public class ComponentPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(ComponentPermissionController.class);

    @Autowired
    private IComponentPermissionService componentService;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    @ResponseBody
    public Page<ComponentPermissionEntity> getPermissionList(@PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<ComponentPermissionEntity> compoPermList = componentService.getComponentList(pageable);

        logger.debug(compoPermList.toString());

        return compoPermList;
    }
}