package com.nicefish.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 这里的 nicefish_cms_post 是索引名称，与库表同名。
 * @author 大漠穷秋
 */
@Document(indexName = "nicefish_cms_post")
public class PostSearchEntity implements Serializable {
    @Id
    @Field(name = "post_id")
    private String postId;

    @Field(name = "thumb_img_url")
    private String thumbImgUrl;

    @Field(name = "header_img_url")
    private String headerImgUrl;

    //TODO:需要在 ES 中配置 IK 分词器来支持中文分词
    @Field(name="post_title",type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    //TODO:需要在 ES 中配置 IK 分词器来支持中文分词
    @Field(name="post_content",type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    //TODO:需要在 ES 中配置 IK 分词器来支持中文分词
    @Field(name="post_summary",type = FieldType.Text, analyzer = "ik_max_word")
    private String summary;

    @Field(name="user_id")
    private Integer userId;

    @Field(name="email",type = FieldType.Text, analyzer = "ik_max_word")
    private String email;

    @Field(name="nick_name",type = FieldType.Text, analyzer = "ik_max_word")
    private String nickName;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
