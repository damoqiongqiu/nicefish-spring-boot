package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-30 20:31
 */
@Repository
public interface ICommentRepository extends JpaRepository<CommentEntity, Integer> {

    Page<CommentEntity> findAllByPostId(Long postId, Pageable pageable);

    Page<CommentEntity> findAllByUserId(Long userId,Pageable pageable);

    Long countByUserId(Long userId);

    Integer deleteById(Long commentId);

    Integer deleteByPostId(Long postId);

    Integer deleteByUserId(Long userId);
}