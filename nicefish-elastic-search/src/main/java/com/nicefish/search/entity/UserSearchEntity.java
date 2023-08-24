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
    @Field("user_id")
    private Integer userId;

    @Field("user_name")
    private String userName;

    @Field("nick_name")
    private String nickName;

    @Field(name="email")
    private String email;

    @Field(name="cellphone")
    private String cellphone;

    @Field(name="gender")
    private Integer gender=0;

    @Field(name="city")
    private String city;

    @Field(name="education")
    private String education;

    @Field(name="avatar_url")
    private String avatarURL;

    @Field(name="status")
    private Integer status=0;

    @Field(name="remark")
    private String remark;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}