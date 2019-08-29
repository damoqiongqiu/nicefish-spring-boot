package com.nicefish.auth.shiro.realm;

import com.nicefish.auth.exception.*;
import com.nicefish.auth.jpa.entity.UserEntity;
import com.nicefish.auth.service.IUserService;
import com.nicefish.auth.shiro.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 大漠穷秋
 */
@Slf4j
public class FishUserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService2;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = usernamePasswordToken.getPassword()!=null?new String(usernamePasswordToken.getPassword()):"";

        UserEntity userEntity = null;
        try {
            userEntity = userService2.checkUser(username, password);
            log.debug("UserName>"+username);
            log.debug("Password>"+password);
        } catch (CaptchaException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info(username + "登录失败{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity, password, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        UserEntity userEntity = ShiroUtil.getSysUser();
        Set<String> roles = new HashSet<String>();
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        if (user.isAdmin()) {
//            info.addRole("admin");
//            info.addStringPermission("*:*:*");
//        }
        return info;
    }
}
