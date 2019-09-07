package com.nicefish.cms.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="cms_params")
public class SysParamEntity implements Serializable {
    @Id
    @Column(name = "ParamKey")
    private String paramKey;
    @Column(name = "ParamValue")
    private String paramValue;
    @Column(name = "ParamDesc")
    private String paramDesc;

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
