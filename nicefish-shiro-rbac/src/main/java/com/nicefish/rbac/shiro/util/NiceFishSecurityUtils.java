package com.nicefish.rbac.shiro.util;

import com.nicefish.rbac.jpa.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

/**
 * 静态工具类，扩展 Shiro 内置的 SecurityUtils 工具类，加上 NiceFish 需要的一些工具方法。
 * @author 大漠穷秋
 */
public class NiceFishSecurityUtils extends SecurityUtils{
    public static UserEntity getUserEntity() {
        UserEntity userEntity = null;
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (!ObjectUtils.isEmpty(obj)) {
            userEntity = new UserEntity();
            BeanUtils.copyProperties(obj, userEntity);
        }
        return userEntity;
    }

    public static void setUserEntity(UserEntity userEntity) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userEntity, realmName);
        subject.runAs(newPrincipalCollection);
    }

    public static Long getUserId() {
        return getUserEntity().getUserId().longValue();
    }

    public static String getUserName() {
        return getUserEntity().getUserName();
    }

    public static String getIp() {
        return SecurityUtils.getSubject().getSession().getHost();
    }

    public static String getSessionId() {
        return String.valueOf(SecurityUtils.getSubject().getSession().getId());
    }

    public static String randomSalt() {
        return new SecureRandomNumberGenerator().nextBytes(3).toHex();
    }
}
