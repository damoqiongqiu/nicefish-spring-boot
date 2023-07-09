package com.nicefish.rbac.jpa.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 此实体类用于持久化 Session 到 MySQL 数据库。
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_session")
public class NiceFishSessionEntity {
    //FIXME:自动生成 UUID
    @Id
    @GeneratedValue
    @Column(name = "session_id")
    private String sessionId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_name",nullable = false)
    private String userName;

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
}
