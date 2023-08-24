package com.nicefish.search.service.impl;

import com.nicefish.search.entity.PostSearchEntity;
import com.nicefish.search.entity.UserSearchEntity;
import com.nicefish.search.repository.UserSearchRepository;
import com.nicefish.search.service.IUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserSearchServiceImpl implements IUserSearchService {

    @Autowired
    private UserSearchRepository userSearchRepository;

    @Override
    public Iterable<UserSearchEntity> getAll() {
        return userSearchRepository.findAll();
    }

    public Page<UserSearchEntity> searchSimilar(UserSearchEntity entity, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userSearchRepository.findByUserNameLikeIgnoreCaseOrNickNameLikeIgnoreCaseOrEmailLikeIgnoreCase(
                entity.getUserName(),
                entity.getNickName(),
                entity.getEmail(),
                pageable
        );
    }
}