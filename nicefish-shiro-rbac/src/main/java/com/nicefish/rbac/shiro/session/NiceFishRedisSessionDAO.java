package com.nicefish.rbac.shiro.session;

import com.nicefish.rbac.shiro.realm.NiceFishRbacRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author 大漠穷秋
 */
public class NiceFishRedisSessionDAO extends AbstractSessionDAO{
    private static final Logger logger = LoggerFactory.getLogger(NiceFishRbacRealm.class);
    
    @Value("${shiro.session.expireTime}")//单位：小时
    private int expireTime;

    //TODO:改成Jedis实现
    private RedisTemplate redisTemplate;

    /**
     * Session存到Redis的时候，key使用StringRedisSerializer，以字符串的形式存储，Session本身用二进制存储。
     * 如果需要把Session本身也序列化成JSON字符串，需要调用redisTemplate.setValueSerializer()，提供自己的序列化工具类实现。
     * @param redisTemplate
     */
    @Autowired(required = true)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.HOURS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Object temp=redisTemplate.opsForValue().get(sessionId);
        if(ObjectUtils.isEmpty(temp)){
            return null;
        }
        Session session=(Session) temp;
        logger.debug(session.toString());
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(ObjectUtils.isEmpty(session)|| StringUtils.isEmpty(session.getId())){
            return;
        }
        session.setTimeout(expireTime*60*60*1000);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.HOURS);
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            redisTemplate.opsForValue().getOperations().delete(session.getId());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return redisTemplate.keys("*");
    }
}