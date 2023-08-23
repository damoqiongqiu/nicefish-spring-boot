package com.nicefish.search.service.impl;

import com.nicefish.search.entity.UserSearchEntity;
import com.nicefish.search.repository.UserSearchRepository;
import com.nicefish.search.service.IUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearchServiceImpl implements IUserSearchService {

    @Autowired
    private UserSearchRepository userSearchRepository;

    @Override
    public Iterable<UserSearchEntity> getAll() {
        return userSearchRepository.findAll();
    }
}