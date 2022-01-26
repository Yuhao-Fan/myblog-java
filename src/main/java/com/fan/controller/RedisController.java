package com.fan.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.fan.common.lang.Result;
import com.fan.entity.Blog;
import com.fan.service.BlogService;
import com.fan.util.RedisUtil;
import com.fan.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @program: springbootdemo
 * @Date: 2019/2/22 15:03
 * @Author: zjjlive
 * @Description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    BlogService blogService;

    @RequiresAuthentication
    @PostMapping("set")
    public Result redisset(String key, @Validated @RequestBody Blog blog
    ){
//        Blog blog =new Blog();
//        blog.setId(1L);
//
//        blog.setTitle("title");
//        blog.setDescription("String.valueOf(20)");
//        blog.setContent("content");
//        blog.setCreated(LocalDateTime.now());
        Blog temp = null;
        if(blog.getId()!=null){
            temp=blogService.getById(blog.getId());

            //只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");
        }else{
            System.out.println("ShiroUtil.getProfile().getId() = " + ShiroUtil.getProfile().getId());
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);

        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.succ(redisUtil.set(key,temp));

//        return redisUtil.set(key,value);
    }

    @GetMapping("get")
    public Result redisget(String key){

        return Result.succ(redisUtil.get(key));
    }

    @PostMapping("expire")
    public Result expire(String key){

        return Result.succ(redisUtil.expire(key,ExpireTime));
    }
}
