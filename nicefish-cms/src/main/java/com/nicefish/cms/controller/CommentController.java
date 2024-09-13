package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.CommentEntity;
import com.nicefish.cms.service.ICommentService;
import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author 大漠穷秋
 */
@RestController
@RequestMapping("/nicefish/cms/comment")
public class CommentController {
	@Autowired
	private ICommentService commentService;

	//TODO:每页显示的条数改为系统配置项
	@RequestMapping(value = "/post-id/{postId}/page/{page}", method = RequestMethod.GET)
	public Page<CommentEntity> getCommentList(@PathVariable(value="postId",required = true) Integer postId, @PathVariable(value="page",required = true) Integer page) {
        PageRequest pageRequest= PageRequest.of(page-1,10, Sort.by(Sort.Direction.DESC,"id"));
        return commentService.findByPostId(postId,pageRequest);
	}

	@RequestMapping(value="/write-comment",method = RequestMethod.POST)
	public AjaxResult writeComment(@RequestBody CommentEntity commentEntity){
        UserEntity user= NiceFishSecurityUtils.getUserEntity();
        commentEntity.setUserId(user.getUserId());
        commentEntity.setUserName(user.getUserName());
        commentEntity.setNickName(user.getNickName());
        commentEntity=commentService.saveComment(commentEntity);
        return AjaxResult.success(commentEntity);
    }

    //TODO:管理员用户不过滤ID，获取全部文章并分页
	@RequestMapping(value = "/manage/comment-table/{page}", method = RequestMethod.GET)
	public Page<CommentEntity> getCommentListByUserId(@PathVariable(value="page",required = true) Integer page) {
        Pageable pageable= PageRequest.of(page-1,10);
        Integer userId= NiceFishSecurityUtils.getUserId();
        return this.commentService.findAllByUserIdAndPaging(userId,pageable);
	}

	@RequestMapping(value = "/manage/delete/{commentId}",method = RequestMethod.DELETE)
	public AjaxResult deleteByCommentId(@PathVariable(value="commentId",required = true) Integer commentId){
        commentService.deleteById(commentId);
		return AjaxResult.success();
	}

    @RequestMapping(value = "/manage/delete-by-post-id/{postId}",method = RequestMethod.DELETE)
    public AjaxResult deletetByPostId(@PathVariable(value="postId",required = true) Integer postId){
        Integer affected=commentService.deletetByPostId(postId);
        return AjaxResult.success(affected);
    }

    @RequestMapping(value = "/manage/delete-by-user-id/{userId}",method = RequestMethod.DELETE)
    public AjaxResult deletetByUserId(@PathVariable(value="userId",required = true) Integer userId){
        Integer affected=commentService.deleteByUserId(userId);
        return AjaxResult.success(affected);
    }
}