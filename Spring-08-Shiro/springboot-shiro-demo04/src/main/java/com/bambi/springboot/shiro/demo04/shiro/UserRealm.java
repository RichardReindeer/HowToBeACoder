package com.bambi.springboot.shiro.demo04.shiro;

import com.bambi.springboot.shiro.demo04.pojo.User;
import com.bambi.springboot.shiro.demo04.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义一个Realm对象
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("这是用来授权的");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("这是用来验证的");
        //根据token获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectUserByUsername(token.getUsername());
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
