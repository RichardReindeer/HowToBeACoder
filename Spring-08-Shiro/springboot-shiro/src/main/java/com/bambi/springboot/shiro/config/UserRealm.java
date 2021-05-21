package com.bambi.springboot.shiro.config;


import com.bambi.springboot.shiro.pojo.User;
import com.bambi.springboot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

//自定义的Realm
//想写自定义的Realm就需要集成这个
public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    //重写两个方法
    //这两个方法分别就是授权和认证 ， 与SpringSecurity一样

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法 doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //拿到当前登录的那个对象
        Subject currentUser = SecurityUtils.getSubject();
        //在实例化SimpleAutherticationInfo的时候添加principal为user，就可以在其他方法中，用subject调用getPrincipal获取
        User user  = (User) currentUser.getPrincipal();//拿到user对象

        //设置权限(set的话是一个set集合)
        info.addStringPermission(user.getPermission());

        //这里不能return null !!!! return null就等于啥也没写了
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证方法 doGetAuthorizationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //认证，用户名 密码 数据库中取
        /*String name = "root";
        String password = "123456";*/
        //将authenticationToken转换成我们认识的token
        //连接真实数据库
        User user = userService.queryUserByName(usernamePasswordToken.getUsername());
        if(user== null){
            System.out.println("用户信息不正确");
            return null;
        }
        //此处登录成功，将登录用户的信息存入session中
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        //密码认证shiro自己做 shiro密码也可以加密

        //正常加密:   MD5加密 MD5盐值加密， MD5不可逆，但是可以被破解，所以加盐值

        //1.获取当前用户的认证
        //2. 获取它要传递密码的对象
        //3. 传递的认证名
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
