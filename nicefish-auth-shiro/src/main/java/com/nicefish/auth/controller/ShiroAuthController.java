package com.nicefish.auth.controller;

import com.nicefish.auth.jpa.entity.UserEntity;
import com.nicefish.auth.service.IUserService;
import com.nicefish.core.web.AjaxResult;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Shiro 相关的权限控制
 * @author 大漠穷秋
 */
@Api("Shiro Auth")
@Controller
@RequestMapping("/nicefish/auth/shiro")
public class ShiroAuthController {
    @Autowired
    protected IUserService userService2;

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String userName, String password, Boolean rememberMe) {
        UserEntity userEntity=new UserEntity();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
            SecurityUtils.getSubject().login(token);

            //TODO:重新整合数据模型，全部统一到UserEntity
            UserEntity userInDB=this.userService2.getUserByUserName(userName);
            if(!ObjectUtils.isEmpty(userInDB)){
                BeanUtils.copyProperties(userInDB,userEntity);
            }
            return AjaxResult.success(userEntity);
        } catch (AuthenticationException e) {
            String msg = "登录失败，用户或密码错误";
            if (!StringUtils.isEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return AjaxResult.success(userEntity);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
