package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.PostEntity;
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
     * @param userId
     * @param postId
     * @return
     */
    UserPostRelationEntity findByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * 判断用户与帖子之间是否存在点赞与收藏关系
     * @param userId
     * @param postId
     * @param relationType
     * @return
     */
    boolean existsByUserIdAndPostIdAndRelationType(@Param("userId") Integer userId, @Param("postId") Integer postId, @Param("relationType") Integer relationType);

    /**
     * 根据 userId, postId, relationType 查找关系记录
     * @param userId
     * @param postId
     * @param relationType
     * @return
     */
    UserPostRelationEntity findByUserIdAndPostIdAndRelationType(Integer userId, Integer postId, Integer relationType);

    /**
     * 根据用户ID查找所有关系记录
     * @param userId
     * @return
     */
    List<UserPostRelationEntity> findAllByUserId(Integer userId);

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
     * 根据用户ID查询该用户的帖子被点赞的总数（使用原生SQL）
     * @param userId
     * @return
     */
    @Query(value =
            "SELECT COUNT(*) AS COUNT FROM nicefish_cms_user_post_relation r " +
            "LEFT OUTER JOIN nicefish_cms_post p ON r.post_id = p.post_id " +
            "WHERE r.relation_type=1 AND p.user_id = :userId", nativeQuery = true)
    int countLikesForUserPosts(@Param("userId") Integer userId);

    /**
     * 根据 userId 查找此用户收藏或者点赞的所有帖子
     * TODO:分页
     * @param userId
     * @param relationType
     * @return
     */
    @Query("SELECT p FROM UserPostRelationEntity upr JOIN PostEntity p ON upr.postId = p.postId WHERE upr.userId = :userId AND upr.relationType = :relationType")
    List<PostEntity> findUserRelatedPosts(@Param("userId") Integer userId, @Param("relationType") Integer relationType);
}