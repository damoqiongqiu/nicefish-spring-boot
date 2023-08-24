package com.nicefish.search.service;

import com.nicefish.search.entity.UserSearchEntity;
import org.springframework.data.domain.Page;

public interface IUserSearchService {
    Iterable<UserSearchEntity> getAll();

    Page<UserSearchEntity> searchSimilar(UserSearchEntity entity, int page, int size);
}