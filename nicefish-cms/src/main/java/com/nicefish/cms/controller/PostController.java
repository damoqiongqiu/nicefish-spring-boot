package com.nicefish.cms.controller;

import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.service.IPostService;
import com.nicefish.core.utils.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 大漠穷秋
 */
@Api("cms-文章接口")
@RestController
@RequestMapping("/nicefish/cms/post")
public class PostController {

    @Autowired
    private IPostService postService;

    //TODO:每页显示的条数改为系统配置项
    @ApiOperation("获取文章列表，按时间倒排，带分页")
    @RequestMapping(value = "/post-list/{page}", method = RequestMethod.GET)
    public Page<PostEntity> getPostList(@PathVariable(value="page",required = false) Integer page) {
        Pageable pageable= PageRequest.of(page-1,10, new Sort(Sort.Direction.DESC,"postId"));
        return postService.getPostsPaging(pageable);
    }

    @ApiOperation("获取文章详情")
    @RequestMapping(value = "/post-detail/{postId}",method = RequestMethod.GET)
    public PostEntity getPostDetail(@PathVariable(value = "postId",required = true) Integer postId){
        return postService.getOne(postId);
    }

    private PostEntity setUserInfoToPostEntity(PostEntity postEntity, HttpSession session){
        Integer userId= NiceFishSecurityUtils.getUserEntity().getUserId();
        String nickName= NiceFishSecurityUtils.getUserEntity().getNickName();
        String email= NiceFishSecurityUtils.getUserEntity().getEmail();

        postEntity.setUserId(userId);
        postEntity.setNickName(nickName);
        postEntity.setEmail(email);
        return postEntity;
    }

    @ApiOperation("新增文章")
    @RequestMapping(value = "/write-post",method = RequestMethod.POST)
    public PostEntity writePost(@RequestBody PostEntity postEntity, HttpSession session){
        this.setUserInfoToPostEntity(postEntity,session);
        return postService.savePost(postEntity);
    }

    @ApiOperation("编辑文章")
    @RequestMapping(value = "/update-post",method = RequestMethod.POST)
    public PostEntity updatePost(@RequestBody PostEntity postEntity, HttpSession session){
        this.setUserInfoToPostEntity(postEntity,session);
        return postService.savePost(postEntity);
    }

    /**
     * 根据 userId 查找此用户发表的内容列表，带分页
     * TODO:管理员用户不过滤ID，获取全部文章并分页
     * @return
     */
    @ApiOperation("根据用户ID获取文章列表，带分页")
    @RequestMapping(value = "/manage/post-table/{userId}/{page}", method = RequestMethod.GET)
    public Page<PostEntity> getPostTableByUserId(
            @PathVariable(value="userId",required = true) Integer userId,
            @PathVariable(value="page",required = true) Integer page
    ) {
        Pageable pageable= PageRequest.of(page-1,10);
        return this.postService.getPostsByUserIdAndPaging(userId,pageable);
    }

    @ApiOperation("删除文章")
    @RequestMapping(value = "/manage/del-post/{postId}",method = RequestMethod.DELETE)
    public AjaxResult delPostById(@PathVariable(value="postId",required = true) Integer postId){
        Integer affected=postService.delPost(postId);
        return AjaxResult.success(affected);
    }
}
