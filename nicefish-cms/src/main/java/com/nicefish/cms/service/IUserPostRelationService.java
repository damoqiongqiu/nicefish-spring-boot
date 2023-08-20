package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.UserPostRelationEntity;

import java.util.List;

public interface IUserPostRelationService {

    /**
     * 保存用户与帖子之间的关系
     */
    void saveUserPostRelation(UserPostRelationEntity userPostRelation);

    /**
     * 根据用户ID和帖子ID查找关系记录
     */
    UserPostRelationEntity findByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * 根据用户ID查找所有关系记录
     */
    List<UserPostRelationEntity> findAllByUserId(Integer userId);

    /**
     * 根据帖子ID查找所有关系记录
     */
    List<UserPostRelationEntity> findAllByPostId(Integer postId);

    /**
     * 删除用户与帖子之间的关系
     */
    void deleteUserPostRelation(UserPostRelationEntity userPostRelation);

    /**
     * 根据帖子ID和关系类型统计数量
     */
    int countByPostIdAndRelationType(Integer postId, Integer relationType);

    int countLikesForUserPosts(Integer userId);
}