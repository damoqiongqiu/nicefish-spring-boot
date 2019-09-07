package com.nicefish.cms.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="cms_comment")
public class CommentEntity implements Serializable {
    @Id
    @Column(name="CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PostId")
    private Long postId;
    @Lob
    @Column(name = "Content",columnDefinition="text")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CommentTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    @Column(name="CommentIp")
    private String ip;
    @Column(name="p_id")
    private Long pId;
    @Column(name = "UserId")
    private Long userId;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "NickName")
    private String nickName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Status")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
