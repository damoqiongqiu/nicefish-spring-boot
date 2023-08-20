package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.UserPostRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserPostRelationRepository extends JpaRepository<UserPostRelationEntity, Integer> {

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
     * 根据帖子ID和关系类型统计数量
     */
    int countByPostIdAndRelationType(Integer postId, Integer relationType);

    /**
     * 根据用户ID查询该用户的帖子被点赞的总数（使用原生SQL）
     */
    @Query(value =
            "SELECT COUNT(*) AS COUNT FROM nicefish_cms_user_post_relation r " +
            "LEFT OUTER JOIN nicefish_cms_post p ON r.post_id = p.post_id " +
            "WHERE r.relation_type=1 AND p.user_id = :userId", nativeQuery = true)
    int countLikesForUserPosts(@Param("userId") Integer userId);
}