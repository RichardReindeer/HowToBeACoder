package com.bambi.springboot.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiger {
    //配置需要倒着配，先配realm对象

    //ShiroFilterFactoryBean  shiro过滤的一些对象
    //需要放到bean中
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //使用set方法将SecurityManager设置为注入到SpringBean中的defaultWebSecurityManager对象
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);


        //添加Shiro的内置过滤器
        /**
         *
         * anon : 无需认证就可以访问
         * authc: 必须认证了才能访问
         * user:  必须拥有 记住我 功能才能用
         * perms: 拥有对某个资源的权限才能访问
         * role:  拥有某个角色权限才能访问
         */

        //拦截
        Map<String, String> filterChainDefinitionMap  = new LinkedHashMap<>();

        //添加权限操作
        //perms权限设置，后面跟一个键值对数组，表示user必须拥有add权限才可以访问
        //没有权限   401---->未授权
        //正常情况下，未授权会跳转到未授权页面
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");

        //向map中添加路径去权限 支持通配符
        /*filterChainDefinitionMap.put("/user/add","authc");
        filterChainDefinitionMap.put("/user/update","authc");*/
        filterChainDefinitionMap.put("/user/*","authc");
        //去测试一下看看还能不能直接访问了
        //不能，会报404错误，因为没有权限

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权页面请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    //2.
    //需要诸如
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联
        //通过Spring绑定需要传递参数
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    //创建 realm对象  ,  需要自定义 1.
    //使用@Bean的方式在Configuration中注入对象
    //Spring注入对象之后默认设置对象名字为方法名，不喜欢可以在@Bean中设置(name = "")
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect   用来整合shiro和Thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
