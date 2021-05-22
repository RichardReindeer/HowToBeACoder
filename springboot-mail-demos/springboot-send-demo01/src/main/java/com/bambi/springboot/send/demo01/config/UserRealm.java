package com.bambi.springboot.send.demo01.config;

import com.bambi.springboot.send.demo01.service.IUserService;
import com.bambi.springboot.send.demo01.vo.UserVo;
import io.swagger.annotations.ApiModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.session.Session;

import javax.annotation.Resource;

@ApiModel("这是一个自定义个Realm类")
public class UserRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        Subject subject = SecurityUtils.getSubject();
        UserVo userVo = (UserVo) subject.getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println(userVo.getPermission());
        simpleAuthorizationInfo.addStringPermission(userVo.getPermission());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("验证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserVo userVo = userService.selectUserByUsername(token.getUsername());
        if(userVo==null){
            return null;
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",userVo);
        return new SimpleAuthenticationInfo(userVo,userVo.getPassword(),"");
    }
}
