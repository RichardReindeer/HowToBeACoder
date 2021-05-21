package com.bambi.springboot.shiro.demo04.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 注入三个部分
     * ShiroFilterFactoryBean
     *
     * SecurityManager
     *
     * Realm
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        //拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/*","authc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        factoryBean.setLoginUrl("/toLogin");
        return factoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

    @Bean
    public UserRealm getUserRealm (){
        return new UserRealm();
    }

}
