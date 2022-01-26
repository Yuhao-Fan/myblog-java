package com.fan.controller;


import com.fan.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController//下面的合体
//@Controller
//@ResponseBody
public class ActionController {
    @Autowired
    ActionService actionService;
//    @GetMapping("blog/like")
//    public Result likeBlog(@RequestParam boolean isadd,
//                           @RequestParam Long blogId
//    ){
//        Blog temp = null;
//        System.out.println("isadd = " + isadd);
//        System.out.println("blogId = " + blogId);
//        temp=blogService.getById(blogId);
//
//        System.out.println("temp.getId() = " + temp.getId());
//        int nowLike=temp.getLikenum();
//        System.out.println("nowLike = " + nowLike);
//        temp.setLikenum(isadd?nowLike-1:nowLike+1);
//        blogService.saveOrUpdate(temp);
//
//
//
//        if(isadd)
//            return Result.succ("Like成功");
//        else return Result.succ("取消Like成功");
//    }
//    @Resource
//    private RedisUtil redisUtil;



}
