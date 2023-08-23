package com.nicefish.cms.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="nicefish_cms_comment")
public class CommentEntity implements Serializable {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="post_id")
    private Integer postId;
    
    @Lob
    @Column(name = "content",columnDefinition="text")
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time=new Date();
    
    @Column(name="comment_ip")
    private String ip;
    
    @Column(name="p_id")
    private Integer pId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "nick_name")
    private String nickName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "status")
    private Integer status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
