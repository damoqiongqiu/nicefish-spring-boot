package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.SysParamEntity;
import com.nicefish.cms.jpa.repository.ISysParamRepository;
import com.nicefish.cms.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大漠穷秋
 */
@Transactional
@Service
public class SysParamServiceImpl implements ISysParamService {

    @Autowired
    private ISysParamRepository sysParamRepository;

    @Override
    @Cacheable(value="params",key ="T(String).valueOf(#pageable.pageNumber).concat('-').concat(#pageable.pageSize)", unless="#result==null")
    public Page<SysParamEntity> getParamPaging(Pageable pageable) {
        return this.sysParamRepository.findAll(pageable);
    }

    @Override
    public Map<String, String> getAllParamJSON() {
        Map<String,String> result=new HashMap<>();
        Iterable<SysParamEntity> iterable=this.sysParamRepository.findAll();
        if(ObjectUtils.isEmpty(iterable)){
            return result;
        }
        for (SysParamEntity sysParamEntity : iterable) {
            result.put(sysParamEntity.getParamKey(),sysParamEntity.getParamValue());
        }
        return result;
    }
}
