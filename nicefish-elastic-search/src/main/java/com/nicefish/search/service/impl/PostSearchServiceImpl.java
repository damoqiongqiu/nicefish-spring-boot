package com.nicefish.search.service.impl;

import org.springframework.data.domain.Page;
import com.nicefish.search.entity.PostSearchEntity;
import com.nicefish.search.repository.PostSearchRepository;
import com.nicefish.search.service.IPostSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostSearchServiceImpl implements IPostSearchService {

    @Autowired
    private PostSearchRepository postSearchRepository;

    @Override
    public Iterable<PostSearchEntity> getAll() {
        return postSearchRepository.findAll();
    }

    @Override
    public Page<PostSearchEntity> searchSimilar(PostSearchEntity entity, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postSearchRepository.findByTitleLikeIgnoreCaseOrContentLikeIgnoreCaseOrSummaryLikeIgnoreCase(
                entity.getTitle(),
                entity.getContent(),
                entity.getSummary(),
                pageable
        );
    }
}