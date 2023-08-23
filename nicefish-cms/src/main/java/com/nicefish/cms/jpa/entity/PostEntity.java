package com.nicefish.cms.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="nicefish_cms_post")
public class PostEntity implements Serializable {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "thumb_img_url")
    private String thumbImgUrl;

    @Column(name = "header_img_url")
    private String headerImgUrl;

    @Column(name="post_title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="post_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime=new Date();

    @Lob
    @Column(name="post_content",columnDefinition="text")
    private String content;

    @Column(name="post_summary")
    private String summary;

    @Column(name="original_url")
    private String originalURL="";

    @Column(name="post_type")
    private String postType="0";

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="update_time")
    private Date updateTime=new Date();

    @Column(name = "read_times")
    private Integer readTimes=1;
    
    @Column(name="liked_times")
    private Integer likedTimes=0;
    
    @Column(name="comment_times")
    private Integer commentTimes=0;
    
    @Column(name="user_id")
    private Integer userId;

    @Basic(optional=true)
    @Column(name="email")
    private String email;
    
    @Column(name="nick_name")
    private String nickName;
    
    @Column(name="enable_comment")
    private String enableComment="Y";
    
    @Column(name="status")
    private Integer status=4;

    //TODO:业务限制，每篇文章最多上传 9 张图片，或者 1 个视频。
    //TODO:需要测试，保存时需要更新关联表。
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "postEntity")
    private List<FileUploadEntity> fileUploadEntities;

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

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Date getLastModifyTime() {
        return updateTime;
    }

    public void setLastModifyTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getLikedTimes() {
        return likedTimes;
    }

    public void setLikedTimes(Integer likedTimes) {
        this.likedTimes = likedTimes;
    }

    public Integer getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
        this.commentTimes = commentTimes;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getThumbImgUrl() {
        return thumbImgUrl;
    }

    public void setThumbImgUrl(String thumbImgUrl) {
        this.thumbImgUrl = thumbImgUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(String enableComment) {
        this.enableComment = enableComment;
    }

    public List<FileUploadEntity> getFileUploadEntities() {
        return fileUploadEntities;
    }

    @JsonProperty
    public void setFileUploadEntities(List<FileUploadEntity> fileUploadEntities) {
        this.fileUploadEntities = fileUploadEntities;
    }
}
