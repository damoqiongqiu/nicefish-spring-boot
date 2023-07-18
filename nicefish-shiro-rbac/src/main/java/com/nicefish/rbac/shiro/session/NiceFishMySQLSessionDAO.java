package com.nicefish.rbac.shiro.session;

import com.nicefish.rbac.jpa.entity.NiceFishSessionEntity;
import com.nicefish.rbac.service.INiceFishSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 扩展 Shiro 内置的 EnterpriseCacheSessionDAO ，操作 MySQL 中的 nicefish_rbac_session 表。
 *
 * 由于 EnterpriseCacheSessionDAO 实现了 CacheManagerAware 接口， Shiro 的 SecurityManager 会自动把 CacheManager 缓存实例注入到此类中，
 * 所以此类中可以直接操作 cacheManager 缓存实例。
 *
 * 此类参考了 spring-session-jdbc 的实现，Session 中的所有 attributes 都会被存储到 SESSION_DATA 列中，但是不把整个 Session 对象序列化到数据库中，
 * 因为在跨项目共享 Session 时，如果 Session 中包含了某项目中特有的类，那么其它项目在反序列化时会失败。
 *
 * @author 大漠穷秋
 */
public class NiceFishMySQLSessionDAO extends EnterpriseCacheSessionDAO {
    @Autowired
    private INiceFishSessionService sessionService;

    /**
     * 从 MySQL 数据库中读取 Session ，父层实现会保证先读取缓存，然后再调用此方法。
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        //把 entity 上的数据拷贝给 session 实例，TODO: 有更好的工具？
        NiceFishSessionEntity entity = sessionService.findDistinctBySessionId(sessionId.toString());
        if(ObjectUtils.isEmpty(entity)){
            return null;
        }

        SimpleSession session=new SimpleSession();
        session.setId(entity.getSessionId());
        session.setTimeout(entity.getTimeout());
        session.setStartTimestamp(entity.getCreationTime());
        session.setLastAccessTime(entity.getLastAccessTime());
        session.setHost(entity.getHost());
        session.setAttribute("appName",entity.getAppName());
        session.setAttribute("userId",entity.getUserId());
        session.setAttribute("userName",entity.getUserName());
        session.setAttribute("exprityTime",entity.getExpiryTime());
        session.setAttribute("maxInactiveInterval",entity.getMaxInactiveInteval());
        session.setExpired(entity.isExpired());
        session.setAttribute("os",entity.getOs());
        session.setAttribute("browser",entity.getBrowser());
        session.setAttribute("userAgent",entity.getUserAgent());
        session.setAttribute("sessionData",entity.getSessionData());
        return session;
    }

    /**
     * 把 Session 更新到 MySQL 数据库，父层实现会保证先更新缓存，然后再调用此方法。
     * 把 SimpleSession 上的数据拷贝给 entity ，然后借助于 entity 更新数据库记录。
     * TODO: 有更好的工具？
     * @param session 类型实际上是 Shiro 的 SimpleSession
     */
    @Override
    protected void doUpdate(Session session) {
        SimpleSession simpleSession=(SimpleSession)session;
        NiceFishSessionEntity entity =new NiceFishSessionEntity();
        entity.setSessionId((String)simpleSession.getId());
        entity.setHost(simpleSession.getHost());
        entity.setCreationTime(simpleSession.getStartTimestamp());
        entity.setLastAccessTime(simpleSession.getLastAccessTime());
        entity.setTimeout(simpleSession.getTimeout());
        entity.setExpired(simpleSession.isExpired());
        entity.setAppName((String)simpleSession.getAttribute("appName"));
        entity.setUserId((Integer)simpleSession.getAttribute("userId"));
        entity.setUserName((String)simpleSession.getAttribute("userName"));
        entity.setExpiryTime((Date)simpleSession.getAttribute("exprityTime"));
        entity.setMaxInactiveInteval((Integer)simpleSession.getAttribute("maxInactiveInterval"));
        entity.setOs((String)simpleSession.getAttribute("os"));
        entity.setBrowser((String)simpleSession.getAttribute("browser"));
        entity.setUserAgent((String)simpleSession.getAttribute("userAgent"));
        entity.setSessionData((String)simpleSession.getAttribute("sessionData"));
        this.sessionService.saveSession(entity);
    }

    /**
     * 把 Session 从 MySQL 数据库中删除，父层实现会保证先删除缓存，然后再调用此方法。
     * NiceFish 不进行物理删除，仅仅把标志位设置成过期状态。
     * @param session 类型实际上是 Shiro 的 SimpleSession
     */
    @Override
    protected void doDelete(Session session) {
        NiceFishSessionEntity sessionEntity=this.sessionService.findDistinctBySessionId((String)session.getId());
        sessionEntity.setExpired(true);
        this.sessionService.saveSession(sessionEntity);
    }
}
