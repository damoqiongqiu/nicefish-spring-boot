package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.UserFollowEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserFollowRepository extends JpaRepository<UserFollowEntity, Integer> {
    @Query("SELECT u FROM UserFollowEntity uf JOIN UserEntity u ON uf.fromId = u.userId WHERE uf.toId = :userId")
    List<UserEntity> findFollowersByUserId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(uf) FROM UserFollowEntity uf WHERE uf.toId = :userId")
    Long countFollowersByUserId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(uf) FROM UserFollowEntity uf WHERE uf.fromId = :userId")
    Long countFollowingByUserId(@Param("userId") Integer userId);

    /**
     * 根据 fromId 和 toId 查找
     * @param fromId
     * @param toId
     * @return
     */
    UserFollowEntity findByFromIdAndToId(@Param("fromId")Integer fromId,@Param("toId")Integer toId);

    /**
     * 是否存在关注关系
     * @param fromId
     * @param toId
     * @return
     */
    boolean existsByFromIdAndToId(@Param("fromId")Integer fromId,@Param("toId")Integer toId);
}