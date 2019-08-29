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
@Table(name="blog_post")
public class PostEntity implements Serializable {
    @Id
    @Column(name="PostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "BgImg")
    private String bgImg;
    @Column(name="PostTitle")
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PostTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    @Lob
    @Column(name="PostContent",columnDefinition="text")
    private String content;
    @Column(name="PostSummary")
    private String summary;
    @Column(name="OriginalUrl")
    private String originalURL="";
    @Column(name="PostType")
    private Integer postType=0;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="LastModifyTime")
    private Date lastModifyTime=new Date();
    @Column(name = "ReadTimes")
    private Long readTimes=1L;
    @Column(name="LikedTimes")
    private Long likedTimes=0L;
    @Column(name="CommentTimes")
    private Long commentTimes=0L;
    @Column(name="UserId")
    private Long userId;
    @Basic(optional=true)
    @Column(name="Email")
    private String email;
    @Column(name="NickName")
    private String nickName;
    @Column(name="EnableComment")
    private Integer enableComment=1;
    @Column(name="Status")
    private Integer status=4;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Integer enableComment) {
        this.enableComment = enableComment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Long readTimes) {
        this.readTimes = readTimes;
    }

    public Long getLikedTimes() {
        return likedTimes;
    }

    public void setLikedTimes(Long likedTimes) {
        this.likedTimes = likedTimes;
    }

    public Long getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Long commentTimes) {
        this.commentTimes = commentTimes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
