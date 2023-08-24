package com.nicefish.search.service;

import org.springframework.data.domain.Page;
import com.nicefish.search.entity.PostSearchEntity;

public interface IPostSearchService {
    Iterable<PostSearchEntity> getAll();

    Page<PostSearchEntity> searchSimilar(PostSearchEntity entity, int page, int size);
}