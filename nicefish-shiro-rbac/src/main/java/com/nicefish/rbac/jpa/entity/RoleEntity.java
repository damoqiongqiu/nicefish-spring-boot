package com.nicefish.rbac.jpa.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_role")
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;
    
    @Column(name="role_name")
    private String roleName;
    
    @Column(name="status",columnDefinition = "int default 0",nullable = false)
    private Integer status;
    
    @Column(name="remark")
    private String remark;
    
    @ManyToMany(mappedBy = "roleEntities",fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;

    @ManyToMany(mappedBy = "roleEntities",fetch = FetchType.LAZY)
    private List<ApiEntity> apiEntities;

    @ManyToMany(mappedBy = "roleEntities",fetch = FetchType.LAZY)
    private List<ComponentEntity> componentEntities;

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<ApiEntity> getApiEntities() {
        return apiEntities;
    }

    public void setApiEntities(List<ApiEntity> apiEntities) {
        this.apiEntities = apiEntities;
    }

    public List<ComponentEntity> getComponentEntities() {
        return componentEntities;
    }

    public void setComponentEntities(List<ComponentEntity> componentEntities) {
        this.componentEntities = componentEntities;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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