package com.nicefish.rbac.shiro.session;

import com.nicefish.core.utils.IPUtil;
import com.nicefish.core.utils.ServletUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 比父类默认多一些处理步骤，把一些额外的属性设置到 Session 的 attributes 里面。
 * 业务代码可以利用这些属性进行一些统计。
 * @author 大漠穷秋
 */
public class FishSessionFactory extends SimpleSessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        Session session=super.createSession(initData);
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();

            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
            String ip= IPUtil.getIpAddr(request);
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            session.setAttribute("os",os);
            session.setAttribute("browser",browser);
            session.setAttribute("ip",ip);
            session.setAttribute("userAgent",userAgent);
        }
        return session;
    }
}
