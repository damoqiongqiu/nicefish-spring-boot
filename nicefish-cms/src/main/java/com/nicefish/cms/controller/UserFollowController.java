package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.UserFollowEntity;
import com.nicefish.cms.service.IUserFollowService;
import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nicefish/cms/user-follow")
public class UserFollowController {
    @Autowired
    private IUserFollowService userFollowService;

    /**
     * 获取用户粉丝列表
     */
    @GetMapping("/followers/{userId}")
    public List<UserEntity> getFollowersByUserId(@PathVariable Integer userId) {
        return userFollowService.findFollowersByUserId(userId);
    }

    /**
     * 获取用户粉丝数量
     */
    @RequestMapping(value = "/follower-count/{userId}",method = RequestMethod.GET)
    public Long countFollowersByUserId(@PathVariable(value="userId",required = true) Integer userId) {
        return userFollowService.countFollowersByUserId(userId);
    }
    
    /**
     * 获取用户关注的人数量
     */
    @RequestMapping(value = "/following-count/{userId}",method = RequestMethod.GET)
    public Long countFollowingByUserId(@PathVariable(value="userId",required = true) Integer userId) {
        return userFollowService.countFollowingByUserId(userId);
    }

    /**
     * 创建关注关系
     */
    @PostMapping("/follow")
    public AjaxResult createFollowRelation(@RequestBody UserFollowEntity userFollowEntity) {
        UserFollowEntity result=userFollowService.createFollowRelation(userFollowEntity);
        return ObjectUtils.isEmpty(result)?AjaxResult.failure():AjaxResult.success();
    }
    
    /**
     * 删除关注关系
     */
    @DeleteMapping("/unfollow")
    public void deleteFollowRelation(@RequestBody UserFollowEntity userFollowEntity) {
        userFollowService.deleteFollowRelation(userFollowEntity);
    }

    @PostMapping("/exists-follow")
    public boolean existsFollow(@RequestBody UserFollowEntity userFollowEntity) {
        return userFollowService.existsFollow(userFollowEntity);
    }
}