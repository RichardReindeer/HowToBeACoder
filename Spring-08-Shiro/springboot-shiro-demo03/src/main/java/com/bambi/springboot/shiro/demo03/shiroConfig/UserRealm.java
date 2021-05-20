package com.bambi.springboot.shiro.demo03.shiroConfig;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 写一个自定义的Realm类
 */
public class UserRealm extends AuthorizingRealm {


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("正在进行授权0");
        return null;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //正常是访问数据库，但是先不访问，用一下假数据
        String username = "root";
        String password = "123456";
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(!token.getUsername().equals(username)){
            System.out.println("用户名错误");
            return null;//返回null会自动识别为用户名错误异常
        }
        System.out.println("正在进行验证");

        /**
         * 系统验证要自己做，shiro不允许获取密码相关信息，shiro会自己帮你验证密码
         */
        return new SimpleAuthenticationInfo("",password,"");
    }
}
