package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    Page<PostEntity> getPostsPaging(Pageable pageable);
    PostEntity getOne(Long id);
    PostEntity savePost(PostEntity postEntity);
    Integer delPost(Long id);
    Long countByUserId(Long userId);
    Page<PostEntity> getPostsByUserIdAndPaging(Long userId,Pageable pageable);
}
