package com.nicefish.rbac.jpa.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@IdClass(UserRoleEntity.class)
@Table(name = "nicefish_rbac_user_role")
public class UserRoleEntity implements Serializable {
    @Id
    @Column(name="user_id")
    private Integer userId;

    @Id
    @Column(name="role_id")
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
