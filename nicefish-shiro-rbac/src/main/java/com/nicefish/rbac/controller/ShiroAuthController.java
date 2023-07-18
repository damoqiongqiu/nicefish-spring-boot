package com.nicefish.rbac.controller;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.service.IUserService;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Shiro 认证和授权相关的 API 。
 * @author 大漠穷秋
 */
@Api("Shiro Auth Controller")
@Controller
@RequestMapping("/nicefish/auth/shiro")
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

            //查库，返回完整的用户信息，TODO:不返回密码
            UserEntity userInDB=this.userService.getUserByUserName(userName);
            if(ObjectUtils.isEmpty(userInDB)){
                return AjaxResult.failure("登录失败，用户名或密码错误。");
            }

            Session session=NiceFishSecurityUtils.getSession();
            session.setAttribute("userId",userInDB.getUserId());
            session.setAttribute("userName",userInDB.getUserName());

            return AjaxResult.success(userInDB);
        } catch (AuthenticationException e) {
            return AjaxResult.failure(e.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@RequestBody UserEntity userEntity) {
        //TODO:与前端代码对接，让前端先加密一次传输过来
        userEntity.setSalt(NiceFishSecurityUtils.randomSalt());
        userEntity.setPassword(userService.encryptPassword(userEntity.getUserName(), userEntity.getPassword(), userEntity.getSalt()));
        return userService.createUser(userEntity);
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
