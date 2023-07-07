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
 *
 * @author 大漠穷秋
 */
public class ShiroUtil {
    public static UserEntity getSysUser() {
        UserEntity userEntity = null;
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (!ObjectUtils.isEmpty(obj)) {
            userEntity = new UserEntity();
            BeanUtils.copyProperties(obj, userEntity);
        }
        return userEntity;
    }

    public static void setSysUser(UserEntity userEntity) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userEntity, realmName);
        subject.runAs(newPrincipalCollection);
    }

    public static Long getUserId() {
        return getSysUser().getUserId().longValue();
    }

    public static String getUserName() {
        return getSysUser().getUserName();
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
