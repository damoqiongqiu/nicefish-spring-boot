package com.nicefish.cms.controller;

import com.nicefish.auth.shiro.util.ShiroUtil;
import com.nicefish.cms.jpa.entity.PostEntity;
import com.nicefish.cms.service.IPostService;
import com.nicefish.core.web.AjaxResult;
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
    public PostEntity getPostDetail(@PathVariable(value = "postId",required = true) Long postId){
        return postService.getOne(postId);
    }

    private PostEntity setUserInfoToPostEntity(PostEntity postEntity, HttpSession session){
        Long userId=ShiroUtil.getSysUser().getUserId();
        String nickName=ShiroUtil.getSysUser().getNickName();
        String email=ShiroUtil.getSysUser().getEmail();

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
    @RequestMapping(value = "/edit-post",method = RequestMethod.POST)
    public PostEntity editPost(@RequestBody PostEntity postEntity, HttpSession session){
        this.setUserInfoToPostEntity(postEntity,session);
        return postService.savePost(postEntity);
    }

    //TODO:管理员用户不过滤ID，获取全部文章并分页
    @ApiOperation("根据用户ID获取文章列表，带分页")
    @RequestMapping(value = "/manage/post-table/{page}", method = RequestMethod.GET)
    public Page<PostEntity> getPostListByUserId(@PathVariable(value="page",required = true) Integer page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Long userId=ShiroUtil.getUserId();
        return this.postService.getPostsByUserIdAndPaging(userId,pageable);
    }

    @ApiOperation("删除文章")
    @RequestMapping(value = "/manage/del-post/{postId}",method = RequestMethod.DELETE)
    public AjaxResult delPostById(@PathVariable(value="postId",required = true) Long postId){
        Integer affected=postService.delPost(postId);
        return AjaxResult.success(affected);
    }
}
