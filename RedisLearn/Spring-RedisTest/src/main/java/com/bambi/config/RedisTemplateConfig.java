package com.bambi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * 编写自定义的RedisTemplate配置类
 */
@Configuration
public class RedisTemplateConfig {

    @Bean
    /**
     * RedisConnectionFactory是连接Lettuce的数据库的连接工厂接口
     */
    public RedisTemplate<String,Object> redisTemplate (RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        //创建数据库连接池   lettuce 底层是netty
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
