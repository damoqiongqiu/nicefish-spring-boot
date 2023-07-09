package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService {
    Page<CommentEntity> findByPostId(Integer postId, Pageable pageable);
    CommentEntity saveComment(CommentEntity commentEntity);
    Page<CommentEntity> findAllByUserIdAndPaging(Integer userId,Pageable pageable);
    Integer countByUserId(Integer userId);
    void deleteById(Integer commentId);
    Integer deletetByPostId(Integer postId);
    Integer deleteByUserId(Integer userId);
}