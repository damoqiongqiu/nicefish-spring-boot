package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.SysParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface ISysParamRepository extends PagingAndSortingRepository<SysParamEntity, Integer>, JpaRepository<SysParamEntity, Integer> {

}
