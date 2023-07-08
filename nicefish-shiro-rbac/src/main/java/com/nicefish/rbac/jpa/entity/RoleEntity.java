package com.nicefish.rbac.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "fish_auth_role")
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
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
        name="fish_auth_role_permission",
        joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
        inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="permission_id")}
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PermissionEntity> permissionEntities;

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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
    public List<PermissionEntity> getPermissionEntities() {
        return permissionEntities;
    }

    @JsonProperty(value = "permissionEntities")
    public void setPermissionEntities(List<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }
}
