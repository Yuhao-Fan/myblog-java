package com.fan.shiro;

import com.fan.entity.User;
import com.fan.service.UserService;
import com.fan.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        AccountProfile accountProfile = (AccountProfile) principalCollection.getPrimaryPrincipal();
        System.out.println("??????????????");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("accountProfile = " + accountProfile);
        authorizationInfo.addRole(accountProfile.getRole());
        return authorizationInfo;

//        info.addStringPermission("user:comment");



        //        Object profile=principalCollection.getPrimaryPrincipal();
//        System.out.println(principalCollection.getPrimaryPrincipal());
//        System.out.println(profile);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addRole("admin");
//        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken=(JwtToken)token;
        //System.out.println(jwtToken.getPrincipal());
        String userId=jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user =userService.getById(Long.valueOf(userId));
        if(user ==null){
            throw new UnknownAccountException("账户不存在");

        }
        if(user.getStatus()==-1){
            throw new LockedAccountException("账户已被锁定");

        }
        AccountProfile profile=new AccountProfile();
        BeanUtils.copyProperties(user,profile);
        System.out.println(profile);
        System.out.println("---------");
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
