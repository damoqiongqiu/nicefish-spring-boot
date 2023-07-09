package com.nicefish.rbac.shiro.session;

import com.nicefish.core.utils.IPUtil;
import com.nicefish.core.utils.ServletUtil;
import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建 Session 的工厂类，用来创建自定义的 Session 数据结构。
 * NiceFishSessionFactory 会把一些额外的属性设置到 Session 的 attributes 里面，方便业务层使用。
 * @author 大漠穷秋
 */
public class NiceFishSessionFactory extends SimpleSessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        Session session=super.createSession(initData);//SimpleSession：id; startTimestamp; stopTimestamp; lastAccessTime; timeout; expired; host; attributes;
        if (initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            String ip= IPUtil.getIpAddr(request);
            session.setAttribute("host",ip);

            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            session.setAttribute("os",os);
            session.setAttribute("browser",browser);
            session.setAttribute("userAgent",userAgent);

            //TODO:加入 userId, userName

        }
        return session;
    }

    /**
     * 把 Session 转换为 NiceFishSessionEntity
     * @param session
     * @return
     */
    public static NiceFishSessionEntity convertSessionToEntity(Session session){
        NiceFishSessionEntity sessionEntity=new NiceFishSessionEntity();
        return sessionEntity;
    }

    /**
     * 把 NiceFishSessionEntity 转换为 Session
     * @param sessionEntity
     * @return
     */
    public static Session convertEntityToSession(NiceFishSessionEntity sessionEntity){
        return null;
    }
}
