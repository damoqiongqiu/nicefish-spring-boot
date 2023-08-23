package com.nicefish.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 这里的 nicefish_rbac_user 是索引名称，与库表同名。
 * @author 大漠穷秋
 */
@Document(indexName = "nicefish_rbac_user")
public class UserSearchEntity implements Serializable {
    @Id
    private Integer userId;

    @Field("user_name")
    private String userName;

    @Field("nick_name")
    private String nickName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}