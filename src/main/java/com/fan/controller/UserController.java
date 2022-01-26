package com.fan.controller;


import com.fan.common.lang.Result;
import com.fan.entity.User;
import com.fan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-01-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user=userService.getById(1L);
        return Result.succ(user);
    }

    @PostMapping("/save")
    public Result index(@Validated @RequestBody User user){

        return Result.succ(user);
    }
}
