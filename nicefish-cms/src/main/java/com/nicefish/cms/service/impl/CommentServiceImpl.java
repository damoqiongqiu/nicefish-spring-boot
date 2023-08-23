package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.CommentEntity;
import com.nicefish.cms.jpa.repository.ICommentRepository;
import com.nicefish.cms.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    @Cacheable(value = "comments",key = "#postId.toString().concat('-comments')")
    public Page<CommentEntity> findByPostId(Integer postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId,pageable);
    }

    @Override
    @CacheEvict(value = "comments",allEntries = true)
    public CommentEntity saveComment(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    @Override
    public Page<CommentEntity> findAllByUserIdAndPaging(Integer userId, Pageable pageable) {
        return commentRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Integer countByUserId(Integer userId) {
        return this.commentRepository.countByUserId(userId);
    }

    @Override
    @CacheEvict(value = "comments",allEntries = true)
    public void deleteById(Integer commentId) {
        this.commentRepository.deleteById(commentId);
    }

    @Override
    public Integer deletetByPostId(Integer postId) {
        return this.commentRepository.deleteByPostId(postId);
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return this.commentRepository.deleteByUserId(userId);
    }
}
