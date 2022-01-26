package com.fan.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fan.common.lang.Result;
import com.fan.entity.Action;
import com.fan.entity.Blog;
import com.fan.entity.User;
import com.fan.service.ActionService;
import com.fan.service.BlogService;
import com.fan.service.UserService;
import com.fan.util.RedisUtil;
import com.fan.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


@RestController//下面的合体
//@Controller
//@ResponseBody
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;
    @Autowired
    ActionService actionService;
    @Resource
    private RedisUtil redisUtil;


    @GetMapping("/blogs")
    public Result list(

            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "1") Integer mode,
            @RequestParam(defaultValue = "1") String Username,
            @RequestParam(defaultValue = "created") String OrderBy,
            @RequestParam(defaultValue = "Desc") String OrderMode,
            HttpServletRequest servletRequest
    ){
        //hutool
        Page page = new Page(currentPage,5);//一页5条记录
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Long userId=1L;
//        HttpServletRequest servletRequest;
//        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String jwt =servletRequest.getHeader("Authorization");
        System.out.println("jwt111");
        System.out.println(jwt);
        if(mode!=1){
            if(jwt.equals(""))
            {
                System.out.println("jwt==null");
                return Result.fail("请登录");
            }
            else userId=ShiroUtil.getProfile().getId();
        }

        //IPage pageData=blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        System.out.println(mode);
        switch(mode){
            case 1 ://所有人

                //语句
                //pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));//倒序筛选
                break;
            case 2 ://只看自己
                blogQueryWrapper.eq("user_id",userId);
                //pageData = blogService.page(page, new QueryWrapper<Blog>().eq("user_id",ShiroUtil.getProfile().getId()).orderByDesc("created"));
                break;
            case 3 ://只看他人
                blogQueryWrapper.ne("user_id",userId);
                //pageData = blogService.page(page, new QueryWrapper<Blog>().ne("user_id",ShiroUtil.getProfile().getId()).orderByDesc("created"));//倒序筛选

            case 4://看某个人
//                blogQueryWrapper=blogService.blogUserList(Username);
                User selectuser=null;
                selectuser=userService.getOne(userQueryWrapper.eq("username",Username));
                System.out.println("slectuser = " + selectuser);
                blogQueryWrapper.eq("user_id",selectuser.getId());

        }
        switch(OrderMode){
            case "Desc" ://倒序
                blogQueryWrapper.orderByDesc(OrderBy);
                break;
            case "Asc" ://正序
                blogQueryWrapper.orderByAsc(OrderBy);

                break;

        }

        IPage pageData=blogService.page(page,blogQueryWrapper);
        return Result.succ("获取成功",pageData);

    }
    @GetMapping("blog/search")
    public Result searchBlog(@RequestParam String Info){
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.like("title",Info)
                .or().like("description", Info)
                .or().like("content", Info);
//                .or().like("us", Info);


        List listdata=blogService.list(blogQueryWrapper);


        return Result.succ(listdata);
    }

    @GetMapping("blog/like")
    public Result likeBlog(@RequestParam boolean isadd,
                             @RequestParam Long blogId
    ){
        Blog temp = null;
//        System.out.println("isadd = " + isadd);
//        System.out.println("blogId = " + blogId);
//
        temp=blogService.getById(blogId);
//        System.out.println("temp.getId() = " + temp.getId());
        int nowLike=temp.getLikenum();
//        System.out.println("nowLike = " + nowLike);
        //添加给blog表，它们的点赞数等等
        temp.setLikenum(isadd?nowLike-1:nowLike+1);
        blogService.saveOrUpdate(temp);
        //////////////////////////////重复
        //找到是否已经存在
        Long userId=1L;
        userId=ShiroUtil.getProfile().getId();//得到userID
        QueryWrapper<Action> actionQueryWrapper = new QueryWrapper<>();
        Action newAction = null;
        newAction=actionService.getOne(actionQueryWrapper.eq("user_id",userId).eq("blog_id",blogId));

        System.out.println("newAction = " + newAction);
        if (newAction==null){
//            Action newAction = null;
            newAction=new Action();
            newAction.setUserId(userId);
            newAction.setBlogId(blogId);
            newAction.setIsLike(true);
        }
        else{
            //如果已有，证明已经点赞过，就取消点赞
//            System.out.println("newAction.getIsLike() = " + newAction.getIsLike());
            if (newAction.getIsLike()||!isadd){
                newAction.setIsLike(false);
            }
            else newAction.setIsLike(true);
        }
        actionService.saveOrUpdate(newAction);
        //////////////////////////////
//        System.out.println("newAction = " + newAction);
        //        if (actionQueryWrapper)
//        actionService.get

        if(isadd)
            return Result.succ("Like成功");
        else return Result.succ("取消Like成功");
    }
    @GetMapping("blog/dislike")
    public Result dislikeBlog(@RequestParam boolean isadd,
                             @RequestParam Long blogId
    ){
        Blog temp = null;
        System.out.println("isadd = " + isadd);
        System.out.println("blogId = " + blogId);
        temp=blogService.getById(blogId);
//        System.out.println("temp.getId() = " + temp.getId());


        int nowDislike=temp.getDislikenum();

        System.out.println("nowDislike = " + nowDislike);
        temp.setDislikenum(isadd?nowDislike+1:nowDislike-1);
        blogService.saveOrUpdate(temp);

        //找到是否已经存在
        Long userId=1L;
        userId=ShiroUtil.getProfile().getId();//得到userID
        QueryWrapper<Action> actionQueryWrapper = new QueryWrapper<>();
        Action newAction = null;
        newAction=actionService.getOne(actionQueryWrapper.eq("user_id",userId).eq("blog_id",blogId));

        System.out.println("newAction = " + newAction);
        if (newAction==null){
//            Action newAction = null;
            newAction=new Action();
            newAction.setUserId(userId);
            newAction.setBlogId(blogId);
            newAction.setIsDislike(true);
        }
        else{
            //如果已有，证明已经点赞过，就取消点赞
//            System.out.println("newAction.getIsLike() = " + newAction.getIsLike());
            if (newAction.getIsDislike()||!isadd){
                newAction.setIsDislike(false);
            }
            else newAction.setIsDislike(true);
        }
        actionService.saveOrUpdate(newAction);


        if(isadd)
            return Result.succ("Dislike成功");
        else return Result.succ("取消Dislike成功");
    }
    @GetMapping("blog/collect")
    public Result collectBlog(@RequestParam boolean isadd,
                              @RequestParam Long blogId
    ){
        Blog temp = null;
        System.out.println("isadd = " + isadd);
        System.out.println("blogId = " + blogId);
        temp=blogService.getById(blogId);
//        System.out.println("temp.getId() = " + temp.getId());


        int nowCollect=temp.getCollectnum();

        System.out.println("nowCollect = " + nowCollect);
        temp.setCollectnum(isadd?nowCollect+1:nowCollect-1);
        blogService.saveOrUpdate(temp);

        //找到是否已经存在
        Long userId=1L;
        userId=ShiroUtil.getProfile().getId();//得到userID
        QueryWrapper<Action> actionQueryWrapper = new QueryWrapper<>();
        Action newAction = null;
        newAction=actionService.getOne(actionQueryWrapper.eq("user_id",userId).eq("blog_id",blogId));

        System.out.println("newAction = " + newAction);
        if (newAction==null){
//            Action newAction = null;
            newAction=new Action();
            newAction.setUserId(userId);
            newAction.setBlogId(blogId);
            newAction.setIsCollection(true);
        }
        else{
            //如果已有，证明已经点赞过，就取消点赞
//            System.out.println("newAction.getIsLike() = " + newAction.getIsLike());
            if (newAction.getIsCollection()||!isadd){
                newAction.setIsCollection(false);
            }
            else newAction.setIsCollection(true);
        }
        actionService.saveOrUpdate(newAction);


        if(isadd)
            return Result.succ("Collection成功");
        else return Result.succ("取消Collection成功");
    }
    @GetMapping("blog/share")
    public Result shareBlog(@RequestParam boolean isadd,
                              @RequestParam Long blogId
    ){
        Blog temp = null;
        System.out.println("isadd = " + isadd);
        System.out.println("blogId = " + blogId);
        temp=blogService.getById(blogId);
//        System.out.println("temp.getId() = " + temp.getId());


        int nowShare=temp.getSharenum();

//        System.out.println("nowShare = " + nowShare);
        temp.setCollectnum(isadd?nowShare+1:nowShare-1);//增加+1减少-1
        blogService.saveOrUpdate(temp);

        //找到是否已经存在
        Long userId=1L;
        userId=ShiroUtil.getProfile().getId();//得到userID
        QueryWrapper<Action> actionQueryWrapper = new QueryWrapper<>();
        Action newAction = null;
        newAction=actionService.getOne(actionQueryWrapper.eq("user_id",userId).eq("blog_id",blogId));

        System.out.println("newAction = " + newAction);
        if (newAction==null){
//            Action newAction = null;
            newAction=new Action();
            newAction.setUserId(userId);
            newAction.setBlogId(blogId);
            newAction.setIsShare(true);
        }
        else{
            //如果已有，证明已经点赞过，就取消点赞
//            System.out.println("newAction.getIsLike() = " + newAction.getIsLike());
            if (newAction.getIsShare()||!isadd){
                newAction.setIsShare(false);
            }
            else newAction.setIsShare(true);
        }
        actionService.saveOrUpdate(newAction);


        if(isadd)
            return Result.succ("Share成功");
        else return Result.succ("取消Share成功");
    }


    //    @GetMapping("/onesblogs")
//    public Result onesblogs(@RequestParam(defaultValue = "1") Integer userId){
//        //hutool
//        Page page = new Page(1,5);//一页5条记录
//        //QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
//        //System.out.println(userId);
//        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().eq("user_id",userId).orderByDesc("created"));//倒序筛选
//
//        return Result.succ(pageData);
//    }
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");

        return Result.succ(blog);
    }
//    @RequiresAuthentication

//    @GetMapping("/myblogs")
//    public Result myblogs(){
//
//        Page page = new Page(1,5);//一页5条记录
//        //QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
//        System.out.println(ShiroUtil.getProfile().getId());
//        Long userId=ShiroUtil.getProfile().getId();
//        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().eq("user_id",userId).orderByDesc("created"));//倒序筛选
//        return Result.succ(pageData);
//
//    }

    @RequiresAuthentication
    @GetMapping("/removeBlog")
    //@校验 @请求体
    public Result remove(@RequestParam Integer blogId){
        Blog temp = null;

        temp=blogService.getById(blogId);
        if(temp==null){
            return Result.fail("没有查到");
        }
        //只能删除自己的文章
        Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");


//        BeanUtil.copyProperties(blog,temp,"id","userId","created",status");
        blogService.removeById(blogId);
        return Result.succ(null);
    }
    @RequiresAuthentication
//@校验 @请求体
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        System.out.println("blog.getTitle() = " + blog.getTitle());
        Blog temp = null;

        if(blog.getId()!=null){
            temp=blogService.getById(blog.getId());
            temp.setLastEdit(LocalDateTime.now());
            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");
        }else{
            System.out.println("ShiroUtil.getProfile().getId() = " + ShiroUtil.getProfile().getId());
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setLastEdit(LocalDateTime.now());
            temp.setStatus(0);

        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status","lastEdit");
        blogService.saveOrUpdate(temp);
        //放入，edit和add
        Page page = new Page(1,5);
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.orderByDesc("created");
        IPage pageData=blogService.page(page,blogQueryWrapper);
        redisUtil.set("1",pageData);
        return Result.succ(null);
    }
    @RequiresAuthentication
    @GetMapping("/blog/myaction")
    public Result myaction(
            @RequestParam(defaultValue = "1") Integer blogId

    ){

        QueryWrapper<Action> actionQueryWrapper = new QueryWrapper<>();
        Long userId= ShiroUtil.getProfile().getId();
        actionQueryWrapper.eq("blog_id",blogId).eq("user_id",userId);
        List listdata=actionService.list(actionQueryWrapper);
//        List listdata=commentService.commentUserList(blogId);

        return Result.succ(listdata);

    }
}
