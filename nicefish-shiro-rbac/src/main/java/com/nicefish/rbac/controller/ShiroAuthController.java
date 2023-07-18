package com.nicefish.rbac.controller;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Shiro 认证和授权相关的 API 。
 * @author 大漠穷秋
 */
@Api("Shiro Auth Controller")
@Controller
@RequestMapping("/nicefish/auth/shiro")
@RequiresPermissions("sys:manage:auth")
public class ShiroAuthController {
    private static final Logger logger = LoggerFactory.getLogger(ShiroAuthController.class);

    @Autowired
    protected IUserService userService;

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String userName, String password, Boolean rememberMe) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
            //调用 Shiro 的 API 尝试登录。
            SecurityUtils.getSubject().login(token);

            //登录成功，查库，返回完整的用户信息，TODO:不返回密码
            UserEntity userInDB=this.userService.getUserByUserName(userName);
            return AjaxResult.success(userInDB);
        } catch (AuthenticationException e) {
            String msg = "登录失败，用户名或密码错误。";
            if (!StringUtils.isEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return AjaxResult.failure(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
