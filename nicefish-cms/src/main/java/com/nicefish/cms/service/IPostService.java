package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    Page<PostEntity> getPostsPaging(Pageable pageable);
    PostEntity getOne(Integer id);
    PostEntity savePost(PostEntity postEntity);
    Integer delPost(Integer id);
    Integer countByUserId(Integer userId);
    Page<PostEntity> getPostsByUserIdAndPaging(Integer userId,Pageable pageable);
}
