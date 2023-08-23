package com.nicefish.cms.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "nicefish_cms_file_upload")
public class FileUploadEntity implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //实际存储用的文件名，一般是哈希字符串
    @Column(name = "file_name")
    private String fileName;

    //显示给用户的名称
    @Column(name = "display_name")
    private String displayName;

    //文件后缀
    @Column(name = "fileSuffix")
    private String fileSuffix;

    //文件的大小
    @Column(name = "file_size")
    private Long fileSize;

    //文件的存储路径，可以是本地磁盘上的路径，也可以是网络上的存储路径
    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    @Column(name = "file_desc")
    private String fileDesc;

    @Column(name = "display_order",columnDefinition = "int default 1")
    private Integer displayOrder=1;

    @Column(name = "up_time")
    private Date upTime=new Date();

    //上传此文件的用户 id ，用户必须登录才能上传文件
    @Column(name = "user_id")
    private Integer userId;

    @JoinTable(
            name="nicefish_cms_post_file_upload",
            joinColumns={@JoinColumn(name="up_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="post_id",referencedColumnName="post_id")}
    )
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore //防止 Jackson 因为循环依赖导致转 JSON 字符串时 stackoverflow
    private PostEntity postEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public Date getUpTime() {
        return upTime;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
    }
}
