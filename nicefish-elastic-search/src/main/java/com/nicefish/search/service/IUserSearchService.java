package com.nicefish.search.service;

import com.nicefish.search.entity.UserSearchEntity;

public interface IUserSearchService {
    Iterable<UserSearchEntity> getAll();
}