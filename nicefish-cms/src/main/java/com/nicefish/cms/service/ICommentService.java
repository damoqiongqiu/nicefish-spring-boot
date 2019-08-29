package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService {
    Page<CommentEntity> findByPostId(Long postId, Pageable pageable);
    CommentEntity saveComment(CommentEntity commentEntity);
    Page<CommentEntity> findAllByUserIdAndPaging(Long userId,Pageable pageable);
    Long countByUserId(Long userId);
    Integer deleteById(Long commentId);
    Integer deletetByPostId(Long postId);
    Integer deleteByUserId(Long userId);
}