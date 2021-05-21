package com.bambi.springboot.shiro.demo05.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 * Shiro的三大部分
 * Subject   <--用户
 * Security Manager  <---进行安全验证
 * Realm   <---真实的数据
 */
@Configuration
public class ShiroConfig {

    //1.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //关联绑定
        shiroFilterFactoryBean.setSecurityManager(manager);
        //拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //如果登录成功，则返回首页
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //如果登录不成功则返回登录页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        return shiroFilterFactoryBean;
    }

    //2.SecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联绑定
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    //3.Realm  建议从后往前部署，逻辑直观不被动
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
