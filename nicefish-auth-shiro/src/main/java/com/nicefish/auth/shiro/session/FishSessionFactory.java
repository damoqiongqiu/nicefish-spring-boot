package com.nicefish.auth.shiro.session;

import com.nicefish.core.web.IPUtil;
import com.nicefish.core.web.ServletUtil;
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

            //把OS/Browser/IP地址解析出来放到Session里面
            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            String ip= IPUtil.getIpAddr(request);
            session.setAttribute("os",os);
            session.setAttribute("browser",browser);
            session.setAttribute("ip",ip);
        }
        return session;
    }
}
