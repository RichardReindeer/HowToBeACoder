package com.bambi.springboot.shiro.demo06.shiroConfig;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro分为三部分
 * Subject  存放用户登录信息
 * SecurityManager 安全验证
 * Realm 真正存放数据的位置
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    //1.ShiroFilterFactoryBean 过滤器对象
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterChainDefinitionMap.put("/user/*","authc");
        bean.setLoginUrl("/toLogin");
        //如果登录用户不对
        bean.setUnauthorizedUrl("/unauth");
        return bean;
    }

    //2.SecurityManager 安全管理器对象
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //3.Realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();

    }}
