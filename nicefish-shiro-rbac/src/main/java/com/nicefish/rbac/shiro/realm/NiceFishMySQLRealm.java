package com.nicefish.rbac.shiro.realm;

import com.nicefish.rbac.exception.*;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NiceFish 操作 MySQL 的 Realm 。
 * @author 大漠穷秋
 */
public class NiceFishMySQLRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(NiceFishMySQLRealm.class);

    @Autowired
    private IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = usernamePasswordToken.getPassword()!=null?new String(usernamePasswordToken.getPassword()):"";

        UserEntity userEntity = null;
        try {
            //调用 User Service 认证用户信息，如果认证失败，抛出对应的异常。
            userEntity = userService.checkUser(username, password);
            logger.debug("UserName>"+username);
            logger.debug("Password>"+password);
        } catch (CaptchaException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            logger.info(username + "登录失败{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }

        //用户认证成功，返回验证信息实例。
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity, password, getName());
        return info;
    }

    /**
     * TODO:实现父类中的授权逻辑。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //addRole
        //addStringPermission

        // UserEntity userEntity = NiceFishSecurityUtils.getUserEntity();
        // Set<String> roles = new HashSet<String>();
        // Set<String> menus = new HashSet<String>();
//        if (user.isAdmin()) {
//            info.addRole("admin");
//            info.addStringPermission("*:*:*");
//        }
        return info;
    }
}
