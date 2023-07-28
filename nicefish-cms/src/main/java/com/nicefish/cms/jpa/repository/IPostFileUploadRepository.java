package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.PostFileUploadEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPostFileUploadRepository extends PagingAndSortingRepository<PostFileUploadEntity, Integer>, JpaSpecificationExecutor {
    @Transactional
    int deleteAllByPostId(Integer postId);
}
