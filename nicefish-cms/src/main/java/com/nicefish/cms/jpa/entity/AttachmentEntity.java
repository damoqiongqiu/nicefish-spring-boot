package com.nicefish.cms.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nicefish_attachment")
public class AttachmentEntity implements Serializable {
    public AttachmentEntity() {
    }

    public AttachmentEntity(Integer id, String path, String name, String suffix) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.suffix = suffix;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 本地地址
     */
    @Column(name = "path")
    private String path;

    /**
     * 文件名
     */

    @Column(name = "name")
    private String name;

    /**
     * 后缀
     */
    @Column(name = "suffix")
    private String suffix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
