package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.jpa.entity.UserPostRelationEntity;

import java.util.List;

public interface IUserPostRelationService {
    /**
     * 根据用户ID和帖子ID查找关系记录
     * @param userId
     * @param postId
     * @return
     */
    UserPostRelationEntity findByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * 根据用户ID查找所有关系记录
     * @param userId
     * @return
     */
    List<UserPostRelationEntity> findAllByUserId(Integer userId);

    /**
     * 根据 userId 查找此用户收藏或者点赞的所有帖子
     * TODO:分页
     * @param userId
     * @param relationType
     * @return
     */
    List<PostEntity> findUserRelatedPosts(Integer userId,Integer relationType);

    /**
     * 根据帖子ID查找所有关系记录
     * @param postId
     * @return
     */
    List<UserPostRelationEntity> findAllByPostId(Integer postId);

    /**
     * 根据帖子ID和关系类型统计数量
     * @param postId
     * @param relationType
     * @return
     */
    int countByPostIdAndRelationType(Integer postId, Integer relationType);

    /**
     * 统计用户内容的获赞总数
     * @param userId
     * @return
     */
    int countLikesForUserPosts(Integer userId);

    /**
     * 判断用户与帖子之间是否存在点赞与收藏关系
     * @param userPostRelationEntity
     * @return
     */
    boolean existsRelation(UserPostRelationEntity userPostRelationEntity);

    /**
     * 保存用户与帖子之间的关系
     * @param userPostRelation
     */
    void saveUserPostRelation(UserPostRelationEntity userPostRelation);

    /**
     * 删除用户与帖子之间的关系
     * @param userPostRelation
     */
    void deleteUserPostRelation(UserPostRelationEntity userPostRelation);
}