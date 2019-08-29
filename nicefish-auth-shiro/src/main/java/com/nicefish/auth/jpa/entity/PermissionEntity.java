package com.nicefish.auth.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "fish_auth_permission")
public class PermissionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionId;
    @Column(name = "permission_str")
    private String permissionStr;
    @Column(name = "remark")
    private String remark;
    @Column(name="status",columnDefinition = "int default 0",nullable = false)
    private Integer status;
    @ManyToMany(mappedBy = "permissionEntities",fetch = FetchType.LAZY)
    private List<RoleEntity> roleEntities;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionStr() {
        return permissionStr;
    }

    public void setPermissionStr(String permissionStr) {
        this.permissionStr = permissionStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonIgnore
    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    @JsonProperty(value = "roleEntities")
    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
