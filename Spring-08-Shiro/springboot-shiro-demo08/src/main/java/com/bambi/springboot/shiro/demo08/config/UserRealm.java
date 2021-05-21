package com.bambi.springboot.shiro.demo08.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.io.Serializable;

/**
 * 自定义Realm
 *
 * Authorization
 *
 * Authentication
 */
public class UserRealm extends AuthorizingRealm implements Serializable {

    //处理授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //处理验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = "user";
        String password = "password";
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        if(!token.getUsername().equals(username)){
            return null;
        }
        return new SimpleAuthenticationInfo("",password,"");
    }
}
