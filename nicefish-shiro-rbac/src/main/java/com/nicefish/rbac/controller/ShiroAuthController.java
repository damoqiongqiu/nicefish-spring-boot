package com.nicefish.rbac.controller;

import com.nicefish.core.i18n.I18nUtil;
import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.exception.CellphoneDuplicateException;
import com.nicefish.rbac.exception.EmailDuplicateException;
import com.nicefish.rbac.exception.UserNameDuplicateException;
import com.nicefish.rbac.jpa.entity.ComponentPermissionEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.service.IComponentPermissionService;
import com.nicefish.rbac.service.IUserService;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Shiro 认证和授权相关的 API 。
 * @author 大漠穷秋
 */
@Controller
@RequestMapping("/nicefish/auth/shiro")
public class ShiroAuthController {
    private static final Logger logger = LoggerFactory.getLogger(ShiroAuthController.class);

    @Autowired
    protected IUserService userService;

    @Autowired
    protected IComponentPermissionService componentPermissionService;

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@RequestBody UserEntity userEntity) {
        //TODO:与前端代码对接，让前端先加密一次传输过来
        userEntity.setSalt(NiceFishSecurityUtils.randomSalt());
        userEntity.setPassword(userService.encryptPassword(userEntity.getUserName(), userEntity.getPassword(), userEntity.getSalt()));

        try {
            userService.createUser(userEntity);
        }catch (UserNameDuplicateException e){
            logger.debug(e.toString());
            return AjaxResult.failure(I18nUtil.getMessage("auth.register.user.mail.failure"));
        }catch (EmailDuplicateException e){
            logger.debug(e.toString());
            return AjaxResult.failure(I18nUtil.getMessage("auth.register.mail.failure"));
        }catch (CellphoneDuplicateException e){
            logger.debug(e.toString());
            return AjaxResult.failure(I18nUtil.getMessage("auth.register.phone.failure"));
        }

        return AjaxResult.success(userEntity);
    }
    
    /**
     * 调用 Shiro 的 API 尝试登录，Shiro 会在内部调用 NiceFishMySQLRealm.doGetAuthenticationInfo 进行验证。
     * 关键调用轨迹：ShiroAuthController.login->NiceFishMySQLRealm.doGetAuthenticationInfo->UserServiceImpl.checkUser
     * TODO:要求前端代码对 password 先进行一次加密再调用此接口登录。
     * @param userName   用户名
     * @param password   密码
     * @param rememberMe 记住我
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String userName, String password, Boolean rememberMe) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
            NiceFishSecurityUtils.getSubject().login(token);
            UserEntity userEntity=NiceFishSecurityUtils.getUserEntity();
            return AjaxResult.success(userEntity);
        } catch (AuthenticationException e) {
            return AjaxResult.failure(e.getMessage());
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            NiceFishSecurityUtils.getSubject().logout();

            //把请求端的所有 cookie 全部标记成失效
            Cookie[] cookies=request.getCookies();
            if(!ObjectUtils.isEmpty(cookies)){
                for(Cookie cookie:cookies){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            return AjaxResult.success();
        } catch (AuthenticationException e) {
            return AjaxResult.failure(e.getMessage());
        }
    }

    @GetMapping(value = "/menu/{userId}")
    @ResponseBody
    public AjaxResult getMenus(@PathVariable(value = "userId",required = true) Integer userId){
        Iterable<ComponentPermissionEntity> list = this.componentPermissionService.getComponentPermissionsByUserId(userId);
        return AjaxResult.success(list);
    }

}
