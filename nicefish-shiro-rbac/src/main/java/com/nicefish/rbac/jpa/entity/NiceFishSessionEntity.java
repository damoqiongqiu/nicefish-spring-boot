package com.nicefish.rbac.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 此实体类用于持久化 Session 到 MySQL 数据库。
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nicefish_rbac_session")
public class NiceFishSessionEntity {
    
}
