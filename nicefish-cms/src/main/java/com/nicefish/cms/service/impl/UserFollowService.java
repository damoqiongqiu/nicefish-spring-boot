package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.repository.IUserFollowRepository;
import com.nicefish.cms.service.IUserFollowService;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowService implements IUserFollowService {

    private final IUserFollowRepository userFollowRepository;

    @Autowired
    public UserFollowService(IUserFollowRepository userFollowRepository) {
        this.userFollowRepository = userFollowRepository;
    }

    @Override
    public List<UserEntity> findFollowersByUserId(Integer userId) {
        return userFollowRepository.findFollowersByUserId(userId);
    }

    @Override
    public Long countFollowersByUserId(Integer userId) {
        return userFollowRepository.countFollowersByUserId(userId);
    }

    @Override
    public Long countFollowingByUserId(Integer userId) {
        return userFollowRepository.countFollowingByUserId(userId);
    }
}
