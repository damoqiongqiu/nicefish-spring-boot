package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface IPostRepository extends PagingAndSortingRepository<PostEntity, Integer> {

    Page<PostEntity> findAllByUserId(Long userId, Pageable pageable);

    Long countByUserId(Long userId);

    PostEntity findDistinctByPostId(Long postId);

    Integer deleteByPostId(Long postId);

}
