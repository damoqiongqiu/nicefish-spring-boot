package com.nicefish.rbac.shiro.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 因为前后端分离开发模式下面，需要处理跨域的问题，这里加上一些协议头，同时删掉了父类上面的跳转流程。
 * 在前后端分离模式下，前端自己跳转，后端不用管。
 * @author 大漠穷秋
 */
public class FishLogoutFilter extends LogoutFilter {
    private static final Logger logger = LoggerFactory.getLogger(FishLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest=WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        //NOTE:这个地方的Allow-Origin不能返回通配符，必须返回前端过来的Origin协议头
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Max-Age", "0");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("XDomainRequestAllowed","1");

        Subject subject = getSubject(request, response);

        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }

        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        return false;
    }
}
