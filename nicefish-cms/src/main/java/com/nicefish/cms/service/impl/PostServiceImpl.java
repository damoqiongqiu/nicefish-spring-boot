package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.jpa.entity.PostFileUploadEntity;
import com.nicefish.cms.jpa.repository.IPostFileUploadRepository;
import com.nicefish.cms.jpa.repository.IPostRepository;
import com.nicefish.cms.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class PostServiceImpl implements IPostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IPostFileUploadRepository postFileUploadRepository;

    @Override
    @Cacheable(value="posts",key ="T(String).valueOf(#pageable.pageNumber).concat('-').concat(#pageable.pageSize)", unless="#result==null")
    public Page getPostsPaging(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "post-detail",key = "T(String).valueOf(#id)", unless="#result==null")
    public PostEntity getOne(Integer postId){
        return this.postRepository.findDistinctByPostId(postId);
    }

    @Override
    @CacheEvict(value = "posts",allEntries = true)
    public PostEntity savePost(PostEntity postEntity) {
        //先尝试保存文章自身的内容
        PostEntity newPostEntity=postRepository.save(postEntity);

        //处理文章和图片，视频之间的关联关系，先删掉当前的关联关系，再插入新的关联关系。
        //NOTE:手动操作，不要自动级联，防止错误删除附件。
        postFileUploadRepository.deleteAllByPostId(newPostEntity.getPostId());

        //取客户端传过来的文件列表
        List<FileUploadEntity> newFileUploadEntities=postEntity.getFileUploadEntities();
        if(!ObjectUtils.isEmpty(newFileUploadEntities)){
            List<PostFileUploadEntity> newPostFileUploadList=new ArrayList<>();
            newFileUploadEntities.forEach(entity->{
                PostFileUploadEntity newPf=new PostFileUploadEntity();
                newPf.setPostId(newPostEntity.getPostId());
                newPf.setUpId(entity.getId());
                newPostFileUploadList.add(newPf);
            });

            this.postFileUploadRepository.saveAll(newPostFileUploadList);
            logger.debug(newPostFileUploadList.toString());
            newPostEntity.setFileUploadEntities(newFileUploadEntities);
        }
        return newPostEntity;
    }

    @Override
    @CacheEvict(value = "posts",allEntries = true)
    public Integer delPost(Integer postId) {
        return this.postRepository.deleteByPostId(postId);
    }

    /**
     * 根据 userId 查找此用户发表的内容列表，带分页
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public Page<PostEntity> getPostsByUserIdAndPaging(Integer userId,Pageable pageable) {
        return postRepository.findAllByUserId(userId,pageable);
    }
}
