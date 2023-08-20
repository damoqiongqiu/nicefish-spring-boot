package com.nicefish.cms.service;

import com.nicefish.rbac.jpa.entity.UserEntity;

import java.util.List;

public interface IUserFollowService {

    List<UserEntity> findFollowersByUserId(Integer userId);

    Long countFollowersByUserId(Integer userId);

    Long countFollowingByUserId(Integer userId);
}