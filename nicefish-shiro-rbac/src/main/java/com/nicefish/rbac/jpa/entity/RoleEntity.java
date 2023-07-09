package com.nicefish.rbac.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    @Column(name="role_key")
    private String roleKey;
    
    @Column(name="status",columnDefinition = "int default 0",nullable = false)
    private Integer status;
    
    @Column(name="remark")
    private String remark;
    
    @ManyToMany(mappedBy = "roleEntities",fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;

    @JoinTable(
        name="nicefish_rbac_role_api",
        joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
        inverseJoinColumns={@JoinColumn(name="api_id",referencedColumnName="api_id")}
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ApiEntity> apiEntities;

    @JoinTable(
        name="nicefish_rbac_role_component",
        joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
        inverseJoinColumns={@JoinColumn(name="component_id",referencedColumnName="component_id")}
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ComponentEntity> componentEntities;

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
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

    @JsonIgnore
    public List<ApiEntity> getApiEntities() {
        return apiEntities;
    }

    @JsonProperty(value = "apiEntities")
    public void setApiEntities(List<ApiEntity> apiEntities) {
        this.apiEntities = apiEntities;
    }

    @JsonIgnore
    public List<ComponentEntity> getComponentEntities() {
        return componentEntities;
    }

    @JsonProperty(value = "componentEntities")
    public void setComponentEntities(List<ComponentEntity> componentEntities) {
        this.componentEntities = componentEntities;
    }
}