package com.fan.controller;


import cn.hutool.core.bean.BeanUtil;
import com.fan.common.lang.Result;
import com.fan.entity.Comment;
import com.fan.service.CommentService;
import com.fan.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController//下面的合体
//@Controller
//@ResponseBody
public class CommentController {
    @Autowired
    CommentService commentService;
//    @RequiresPermissions("user:comment")
//    @RequiresPermissions("admin:test3")
    @RequiresRoles("admin")
    @GetMapping("/comment")
    public Result list(
            @RequestParam(defaultValue = "1") Integer blogId

    ){

//        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
//        commentQueryWrapper.eq("blog_id",blogId).orderByDesc("created");
//        List listdata=commentService.list(commentQueryWrapper);
        List listdata=commentService.commentUserList(blogId);

        return Result.succ(listdata);

    }
//    @RequiresAuthentication
    @PostMapping("/comment/add")
    public Result add(@Validated @RequestBody Comment comment){
//        System.out.println("blog.getTitle() = " + blog.getTitle());
//        Comment temp = null;

//        System.out.println("ShiroUtil.getProfile().getId() = " + ShiroUtil.getProfile().getId());
        Comment temp=new Comment();
        temp.setUserId(ShiroUtil.getProfile().getId());
        temp.setCreated(LocalDateTime.now());
        temp.setStatus(0);



        BeanUtil.copyProperties(comment,temp,"id","userId","created","status");
        commentService.saveOrUpdate(temp);

        return Result.succ("评论成功");
    }
    @GetMapping("/comment/received")
    public Result received(){
        int userId= Math.toIntExact(ShiroUtil.getProfile().getId());
        List listdata=commentService.receivedComment(userId);

        return Result.succ(listdata);
    }


}
