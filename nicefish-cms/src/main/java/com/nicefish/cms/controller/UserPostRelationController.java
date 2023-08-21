package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.jpa.entity.UserPostRelationEntity;
import com.nicefish.cms.service.IUserPostRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nicefish/cms/user-post-relation")
public class UserPostRelationController {

    private final IUserPostRelationService userPostRelationService;

    @Autowired
    public UserPostRelationController(IUserPostRelationService userPostRelationService) {
        this.userPostRelationService = userPostRelationService;
    }

    /**
     * 根据用户ID和帖子ID查找关系记录
     */
    @GetMapping("/find-by-user-id-and-post-id/{userId}/{postId}")
    public UserPostRelationEntity findByUserIdAndPostId(@PathVariable Integer userId, @PathVariable Integer postId) {
        return userPostRelationService.findByUserIdAndPostId(userId, postId);
    }

    /**
     * 根据用户ID查找所有关系记录
     */
    @GetMapping("/find-all-by-user-id/{userId}")
    public List<UserPostRelationEntity> findAllByUserId(@PathVariable Integer userId) {
        return userPostRelationService.findAllByUserId(userId);
    }

    /**
     * 根据帖子ID查找所有关系记录
     */
    @GetMapping("/find-all-by-post-id/{postId}")
    public List<UserPostRelationEntity> findAllByPostId(@PathVariable Integer postId) {
        return userPostRelationService.findAllByPostId(postId);
    }

    /**
     * 根据帖子ID统计点赞数量
     */
    @GetMapping("/count-likes/{postId}")
    public int countLikesByPostId(@PathVariable Integer postId) {
        return userPostRelationService.countByPostIdAndRelationType(postId, 1); // 1 表示喜欢
    }

    /**
     * 根据帖子ID统计收藏数量
     */
    @GetMapping("/count-favorites/{postId}")
    public int countFavoritesByPostId(@PathVariable Integer postId) {
        return userPostRelationService.countByPostIdAndRelationType(postId, 2); // 2 表示收藏
    }

    /**
     * 根据用户ID查询该用户的帖子被点赞的总数
     */
    @GetMapping("/count-likes-for-user-posts/{userId}")
    public int countLikesForUserPosts(@PathVariable Integer userId) {
        return userPostRelationService.countLikesForUserPosts(userId);
    }

    /**
     * 判断用户与内容之间是否存在喜欢、收藏关系
     * @param userPostRelationEntity
     * @return
     */
    @PostMapping("/exists-relation")
    public boolean existsRelation(@RequestBody UserPostRelationEntity userPostRelationEntity){
        return userPostRelationService.existsRelation(userPostRelationEntity);
    }

    /**
     * 保存用户与帖子之间的关系
     */
    @PostMapping("/save-relation")
    public void saveUserPostRelation(@RequestBody UserPostRelationEntity userPostRelation) {
        userPostRelationService.saveUserPostRelation(userPostRelation);
    }

    /**
     * 删除用户与帖子之间的关系
     */
    @DeleteMapping("/delete-relation")
    public void deleteUserPostRelation(@RequestBody UserPostRelationEntity userPostRelation) {
        userPostRelationService.deleteUserPostRelation(userPostRelation);
    }

    /**
     * 根据用户ID查询该用户点赞或者收藏的帖子列表
     */
    @PostMapping("/find-user-related-posts")
    public List<PostEntity> findUserRelatedPosts(@RequestBody UserPostRelationEntity userPostRelation) {
        return userPostRelationService.findUserRelatedPosts(userPostRelation.getUserId(),userPostRelation.getRelationType());
    }
}
