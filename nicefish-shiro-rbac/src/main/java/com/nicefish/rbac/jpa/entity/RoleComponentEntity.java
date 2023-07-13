package com.nicefish.rbac.jpa.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_role_component")
public class RoleComponentEntity implements Serializable {
    @Id

    
    @Column(name="role_id")
    private Integer roleId;

    @Column(name="component_id")
    private Integer compPermId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getComponentId() {
        return compPermId;
    }

    public void setComponentId(Integer compPermId) {
        this.compPermId = compPermId;
    }
}
