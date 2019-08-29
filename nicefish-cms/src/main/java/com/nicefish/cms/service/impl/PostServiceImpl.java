package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.jpa.repository.IPostRepository;
import com.nicefish.cms.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    @Cacheable(value="posts",key ="T(String).valueOf(#var1.pageNumber).concat('-').concat(#var1.pageSize)", unless="#result==null")
    public Page getPostsPaging(Pageable var1) {
        return postRepository.findAll(var1);
    }

    @Override
    @Cacheable(value = "post-detail",key = "T(String).valueOf(#id)", unless="#result==null")
    public PostEntity getOne(Long postId){
        return this.postRepository.findDistinctByPostId(postId);
    }

    //TODO:重新设计posts这个缓存的KV结构，避免清除整个缓存
    @Override
    @CacheEvict(value = "posts",allEntries = true)
    public PostEntity savePost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @Override
    @CacheEvict(value = "posts",allEntries = true)
    public Integer delPost(Long postId) {
        return this.postRepository.deleteByPostId(postId);
    }

    @Override
    public Long countByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    public Page<PostEntity> getPostsByUserIdAndPaging(Long userId,Pageable pageable) {
        return postRepository.findAllByUserId(userId,pageable);
    }
}
