package com.nicefish.rbac.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * 此实体类用于持久化 Session 到 MySQL 数据库。
 * 关于 Session 数据结构的注意点 @see com.nicefish.rbac.shiro.session.NiceFishMySQLSessionDAO
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_session")
public class NiceFishSessionEntity {
    //FIXME:自动生成 UUID
    @Id
    @Column(name = "session_id")
    private String sessionId= UUID.randomUUID().toString();

    @Column(name="app_name",nullable = false)
    private String appName;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_name",nullable = false)
    private String userName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="expiry_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiryTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_access_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastAccessTime;

    @Column(name="max_interactive_interval")
    private Integer maxInteractiveInteval;

    @Column(name="timeout")
    private Integer timeout;

    @Column(name="expired")
    private String expired;

    @Column(name="host",nullable = false)
    private String host;

    @Column(name="os",nullable = false)
    private String os;

    @Column(name="browser",nullable = false)
    private String browser;

    @Column(name="userAgent",nullable = false)
    private String userAgent;

    @Lob
    @Column(name="session_data",columnDefinition="text")
    private String sessionData;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Integer getMaxInteractiveInteval() {
        return maxInteractiveInteval;
    }

    public void setMaxInteractiveInteval(Integer maxInteractiveInteval) {
        this.maxInteractiveInteval = maxInteractiveInteval;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSessionData() {
        return sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }
}
