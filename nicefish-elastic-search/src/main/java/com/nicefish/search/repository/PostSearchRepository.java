package com.nicefish.search.repository;

import com.nicefish.search.entity.PostSearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSearchRepository extends ElasticsearchRepository<PostSearchEntity, String> {
    //TODO:重构这里的代码
    Page<PostSearchEntity> findByTitleLikeIgnoreCaseOrContentLikeIgnoreCaseOrSummaryLikeIgnoreCase(
            String title,
            String content,
            String summary,
            Pageable pageable
    );
}