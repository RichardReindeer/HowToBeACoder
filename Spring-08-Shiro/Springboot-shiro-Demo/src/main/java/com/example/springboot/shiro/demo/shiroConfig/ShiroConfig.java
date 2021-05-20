package com.example.springboot.shiro.demo.shiroConfig;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 跟SpringSecurity一样，需要编写一个控制器类
 * 将三个主要层之间进行关联
 */
@Configuration
public class ShiroConfig {

    //配置的时候最好倒着配，这样能够更好的理解整个套用的过程

    //ShiroFilterFactorBean 过滤一些对象
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }

    //SecurityManager
    //想要调用Spring中注入的对象，需要传参数，名字spring默认是使用的方法名
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //Realm
    //将自定义的Realm注入到Spring中
    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
