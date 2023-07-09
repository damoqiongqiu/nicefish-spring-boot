package com.nicefish.rbac.shiro.session;

import com.nicefish.core.utils.IPUtil;
import com.nicefish.core.utils.ServletUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SimpleSessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Session 工厂类，扩展 SimpleSessionFactory ，把一些额外的属性设置到 Session 实例的 attributes 里面，方便业务层使用。
 * Shiro 在运行时会使用此类来创建 Session 实例，配置在 ShiroConfig.java 中。
 * @author 大漠穷秋
 */
public class NiceFishSessionFactory extends SimpleSessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        //父类创建出来的实际上是 SimpleSession 类型的实例
        Session session=super.createSession(initData);
        if (initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            String ip= IPUtil.getIpAddr(request);
            session.setAttribute("host",ip);

            String userAgentString=ServletUtil.getRequest().getHeader("User-Agent");
            session.setAttribute("userAgent",userAgentString);

            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();
            session.setAttribute("os",os);
            session.setAttribute("browser",browser);

            //在 session 中默认添加 userId 和 userName
            Object userId=request.getAttribute("userId");
            if(!ObjectUtils.isEmpty(userId)){
                if(StringUtils.isNumeric(userId.toString())){
                    session.setAttribute("userId", NumberUtils.createInteger(userId.toString()));
                }
            }

            Object userName=request.getAttribute("userName");
            if(!ObjectUtils.isEmpty(userName)){
                session.setAttribute("userName", userName);
            }

            //FIXME:增加对 sessionData 字段的处理
        }
        return session;
    }
}
