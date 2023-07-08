package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.SysParamEntity;
import com.nicefish.cms.service.ISysParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 大漠穷秋
 */
@Api("cms-系统配置参数接口")
@RestController
@RequestMapping("/nicefish/cms/param")
public class SysParamController {
    @Autowired
    private ISysParamService sysParamService;

    //TODO:每页显示的条数改为系统配置项
    @ApiOperation("获取所有配置参数，带分页")
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public Page<SysParamEntity> getParamList(@PathVariable(value="page",required = false) Integer page) {
        Pageable pageable= PageRequest.of(page-1,10, new Sort(Sort.Direction.DESC,"paramKey"));
        return this.sysParamService.getParamPaging(pageable);
    }

    @ApiOperation("获取全部配置参数，不分页")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String,String> getParamAll() {
        return this.sysParamService.getAllParamJSON();
    }
}
