package com.bambi.springboot.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
