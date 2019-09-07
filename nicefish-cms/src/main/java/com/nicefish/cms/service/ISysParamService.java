package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.SysParamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author 大漠穷秋
 */
public interface ISysParamService {
    Page<SysParamEntity> getParamPaging(Pageable pageable);

    Map<String,String> getAllParamJSON();
}
