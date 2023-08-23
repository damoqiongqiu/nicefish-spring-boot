package com.nicefish.cms.jpa.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * 文章与上传的图片，视频之间的关联表
 */
@Entity
@DynamicInsert
@DynamicUpdate
@IdClass(PostFileUploadEntity.class)
@Table(name = "nicefish_cms_post_file_upload")
public class PostFileUploadEntity implements Serializable {
    @Id
    @Column(name="post_id")
    private Integer postId;

    @Id
    @Column(name="up_id")
    private Integer upId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUpId() {
        return upId;
    }

    public void setUpId(Integer upId) {
        this.upId = upId;
    }
}
