package com.nicefish.rbac.controller;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.service.IUserService;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * TODO:完成整合和重构，包括权限 shiro 控制的部分
 * @author 大漠穷秋
 */
@Api("User Controller")
@RestController
@RequestMapping("/nicefish/auth/user")
@RequiresPermissions("sys:manage:user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    protected IUserService userService;

    /**
     * 获取Session中的用户实例。
     * 在前后端分离的模式下，前端可以通过此接口来测试Session中是否还存在已登录的用户。
     * @return
     */
    @GetMapping("/getSessionUser")
    @ResponseBody
    public UserEntity getSessionUser(){
        return NiceFishSecurityUtils.getUserEntity();
    }

    @PostMapping("/update")
    @ResponseBody
    public AjaxResult updateUser(@RequestBody UserEntity userEntity) {
        logger.debug(userEntity.toString());

        //TODO:与前端代码对接，让前端先加密一次传输过来
        userEntity.setSalt(NiceFishSecurityUtils.randomSalt());
        userEntity.setPassword(userService.encryptPassword(userEntity.getUserName(), userEntity.getPassword(), userEntity.getSalt()));

        //TODO:数据合法性校验
        return AjaxResult.success(userService.saveUser(userEntity));
    }

    @RequestMapping(value = "/update-user-role-relation",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateUserRoleRelation(@RequestBody UserEntity userEntity){
        return this.userService.updateUserRoleRelation(userEntity);
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    @ResponseBody
    public Page<UserEntity> getUserList(@RequestBody UserEntity userEntity, @PathVariable(value="page",required = true) int page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Page<UserEntity> userList = userService.getUserList(userEntity,pageable);
        return userList;
    }

    @RequestMapping(value = "/delete/{userId}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteUser(@PathVariable(value="userId",required = true)Integer userId){
        //TODO:合法性校验，关联表操作校验，业务逻辑校验
        int affected=userService.deleteByUserId(userId);
        //TODO:消息国际化
        if(affected==0){
            return new AjaxResult(false,"删除失败，内置用户或者正在使用中。");
        }else{
            return new AjaxResult(true,"删除成功");
        }
    }

    @RequestMapping(value = "/detail/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getUserDetail(@PathVariable(value = "userId",required = true) Integer userId){
        UserEntity userEntity=userService.getUserByUserId(userId);
        AjaxResult ajaxResult =new AjaxResult(true,userEntity);
        return ajaxResult;
    }

    //TODO:重新定义权限，管理员可以重置任意密码，用户自己只能重置自己的密码
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(UserEntity userEntity) {
        userEntity.setSalt(NiceFishSecurityUtils.randomSalt());
        userEntity.setPassword(userService.encryptPassword(userEntity.getUserName(), userEntity.getPassword(), userEntity.getSalt()));
        userService.resetPwd(userEntity);
        return AjaxResult.failure();
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult delUser(Integer[] ids) {
        try {
            return AjaxResult.success(userService.deleteByIds(ids));
        } catch (Exception e) {
            return AjaxResult.failure(e.getMessage());
        }
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(UserEntity userEntity) {
        return AjaxResult.success(userService.setUserStatus(userEntity));
    }

    @PostMapping("/isUserNameUnique")
    @ResponseBody
    public String isUserNameUnique(UserEntity userEntity) {
        return userService.isUserNameUnique(userEntity.getUserName())?"0":"1";
    }

    @PostMapping("/isPhoneUnique")
    @ResponseBody
    public String isPhoneUnique(UserEntity userEntity) {
        return userService.isPhoneUnique(userEntity.getCellphone())?"0":"1";
    }

    @PostMapping("/isEmailUnique")
    @ResponseBody
    public String isEmailUnique(UserEntity userEntity) {
        return userService.isEmailUnique(userEntity.getEmail())?"0":"1";
    }
}