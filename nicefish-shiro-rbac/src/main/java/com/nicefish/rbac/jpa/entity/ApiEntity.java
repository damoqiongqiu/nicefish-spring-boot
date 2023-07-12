package com.nicefish.rbac.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 在 NiceFish 中，权限控制整体上分2个层次：前端权限和后端权限。
 * - 前端权限是指在前端页面上的权限控制，比如菜单、按钮、甚至可以细致到一个 HTML 元素。
 * - 后端权限是指在后端 API 接口上的权限控制，比如一个 API 接口是否需要登录才能访问，是否需要某个角色才能访问，是否需要某个权限才能访问等。
 * 
 * ApiEntity 用来定义服务端 API 接口的权限，对应数据库中的 nicefish_rbac_api 表。
 * 
 * @see ComponentEntity
 * @author 大漠穷秋
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_api")
public class ApiEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="api_id")
    private Integer apiId;

    @Column(name="api_name")
    private String apiName;
    
    @Column(name="url")
    private String url;

    @Column(name="permission")
    private String permission;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time",updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name="remark")
    private String remark;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
