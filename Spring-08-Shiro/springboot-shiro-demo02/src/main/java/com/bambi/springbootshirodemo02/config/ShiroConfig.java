package com.bambi.springbootshirodemo02.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 编写shiro的控制器类
 */
@Configuration
public class ShiroConfig {
    //需要相继配置 ShiroFilterFactory  SecurityManager Realm 从后往前配置方便理解，且更主动

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //关联
        shiroFilterFactoryBean.setSecurityManager(manager);

        //设置拦截
        /**
         * 权限介绍
         * anon: 任何权限都可以访问
         * authc : 需要配置权限才可以访问
         * user:   必须拥有 记住我 才可以访问
         * perms : 拥有对某个资源的权限才能访问
         * role: 拥有某个角色权限才可以访问
         */
        //拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterChainDefinitionMap.put("/user/*","authc");
        shiroFilterFactoryBean.setLoginUrl("/login");


        return shiroFilterFactoryBean;
    }


    //SecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关系绑定 关联
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //Realm 设置一个自己的Realm
    //直接使用@Bean注入
    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }
}
