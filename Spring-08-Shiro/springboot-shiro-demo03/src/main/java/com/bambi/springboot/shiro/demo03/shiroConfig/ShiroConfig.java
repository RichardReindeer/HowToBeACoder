package com.bambi.springboot.shiro.demo03.shiroConfig;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置文件
 * shiro的三大部分
 * Subject  用户信息
 * SecurityManager 安全验证
 * Realm 真正的数据信息
 */
@Configuration
public class ShiroConfig {

    //需要将这些信息全部注入到config中

    //ShiroFilterFactoryBean

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);
        /**
         * shiro中的权限
         * anon : 不需要权限就可以访问
         * authc : 需要特定权限才可以访问
         * user : 需要 记住我 才可以访问
         * perms : 需要拥有对某个资源的权限才可以访问
         * roles: 需要拥有某个角色才可以访问
         */
        //拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        return shiroFilterFactoryBean;
    }

    //SecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //Realm
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
