package com.bambi.springboot.shiro.demo08.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 * shiro的三大部分 ， Subject : 应用代码直接交互的对象，是对外核心api，
 *                  与subject所有交互都会交给SecurityManager ; Subject只是一个门面，SecurityManager才是执行者
 *                  SecurityManager: 安全管理器，所有有关安全的擦欧总都会交给SecuriyManager交互，并且管理所有的Subject
 *                  是shiro的核心，相当于SpringMVC的DispatcherServlet
 *                  Realm: 存放安全数据，just like dataSource
 */
@Configuration
public class ShiroConfig {

    //1.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //设置拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/*","authc");
        bean.setLoginUrl("/toLogin");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }


    //2.SecurityManager
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //这是关联关系
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    //3.Realm  将自定义的Realm使用@Bean的方式注入到ShiroConfig中
    @Bean
    public UserRealm getUserRealm (){
        return new UserRealm();
    }
}
