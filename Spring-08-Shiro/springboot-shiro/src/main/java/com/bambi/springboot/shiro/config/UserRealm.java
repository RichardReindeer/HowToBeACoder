package com.bambi.springboot.shiro.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

//自定义的Realm
//想写自定义的Realm就需要集成这个
public class UserRealm extends AuthorizingRealm {

    //重写两个方法
    //这两个方法分别就是授权和认证 ， 与SpringSecurity一样

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法 doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证方法 doGetAuthorizationInfo");

        //认证，用户名 密码 数据库中取
        String name = "root";
        String password = "123456";
        //将authenticationToken转换成我们认识的token

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if(!usernamePasswordToken.getUsername().equals(name)){
            return null;//如果return null 自动抛出异常
        }
        //密码认证shiro自己做
        //1.获取当前用户的认证
        //2. 获取它要传递密码的对象
        //3. 传递的认证名
        return new SimpleAuthenticationInfo("",password,"");
    }
}
