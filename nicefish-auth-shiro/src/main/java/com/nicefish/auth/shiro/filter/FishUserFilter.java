package com.nicefish.auth.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Debug用，没有修改父类上的实现。
 * @author 大漠穷秋
 */
public class FishUserFilter extends UserFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
