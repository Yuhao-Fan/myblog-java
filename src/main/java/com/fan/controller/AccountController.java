package com.fan.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fan.common.dto.LoginDto;
import com.fan.common.lang.Result;
import com.fan.entity.User;
import com.fan.service.UserService;
import com.fan.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        //QueryWrapper数据库
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");//断言
        //加密
        System.out.println(user.getPassword());
        //密码在数据库中加密md5：96e79218965eb72c92a549dd5a330112
        if(!user.getPassword().equals( SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }
        //登录成功后创建token
        String jwt = jwtUtils.generateToken(user.getId());
        //返回jwt的Header信息
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");


        return Result.succ("登录成功",MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .put("role",user.getRole())
                .map()

        );
    }
    @PostMapping("/signup")
    public Result signup(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        //QueryWrapper数据库
//        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
//        Assert.isNull(user,"用户名已存在");//断言
        QueryWrapper<User> QueryWrapper = new QueryWrapper<>();
        System.out.println(userService.getOne(QueryWrapper.eq("username", loginDto.getUsername())));
        if(userService.getOne(QueryWrapper.eq("username", loginDto.getUsername()))!=null){
//            System.out.println();

            return Result.fail("用户名已存在");
        }
        //加密
//        System.out.println(user.getPassword());

        User temp=new User();
        temp.setUsername(loginDto.getUsername());
        temp.setCreated(LocalDateTime.now());
        temp.setPassword(SecureUtil.md5(loginDto.getPassword()));
        temp.setStatus(0);
        userService.save(temp);

        //密码在数据库中加密md5：96e79218965eb72c92a549dd5a330112

        return Result.succ("注册成功"
//                ,MapUtil.builder()
//                .put("id",user.getId())
//                .put("username",user.getUsername())
//                .put("avatar",user.getAvatar())
//                .put("email",user.getEmail())
//                .map()

        );
    }
    @GetMapping("/logout")
    public Result logout(){
        //交给Shiro
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
