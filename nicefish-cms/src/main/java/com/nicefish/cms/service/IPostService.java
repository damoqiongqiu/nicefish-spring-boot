package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    /**
     * 查找内容列表，不区分用户
     * @param pageable
     * @return
     */
    Page<PostEntity> getPostsPaging(Pageable pageable);

    PostEntity getOne(Integer id);

    PostEntity savePost(PostEntity postEntity);

    Integer delPost(Integer id);

    /**
     * 根据 userId 查找此用户发表的内容列表，带分页
     * @param userId
     * @param pageable
     * @return
     */
    Page<PostEntity> getPostsByUserIdAndPaging(Integer userId,Pageable pageable);
}
