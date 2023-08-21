package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.UserFollowEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;

import java.util.List;

public interface IUserFollowService {

    List<UserEntity> findFollowersByUserId(Integer userId);

    Long countFollowersByUserId(Integer userId);

    Long countFollowingByUserId(Integer userId);

    UserFollowEntity createFollowRelation(UserFollowEntity userFollowEntity);

    void deleteFollowRelation(UserFollowEntity userFollowEntity);

    boolean existsFollow(UserFollowEntity userFollowEntity);
}