package com.bambi.springboot.shiro.demo05.config;

import com.bambi.springboot.shiro.demo05.service.IUserService;
import com.bambi.springboot.shiro.demo05.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * 自己写的Realm配置类
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始进行授权");
        //AuthorizationInfo是一个接口，创建一个关于它的实例
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        UserVo userVo  = (UserVo) subject.getPrincipal();
        //添加一个权限
        info.addStringPermission(userVo.getPermission());
        return info;
    }


    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始进行验证");
        //开始进行验证
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserVo userVo = userService.selectUserByUsername(token.getUsername());
        //根据用户是否为空判断
        if(userVo==null){
            return null;
        }
        //通过在principal来在方法间传递对象
        return new SimpleAuthenticationInfo(userVo,userVo.getPassword(),"");
    }
}
