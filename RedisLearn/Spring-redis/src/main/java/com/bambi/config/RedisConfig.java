package com.bambi.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    /**
     * 编写自己的RedisTemplate
     *
     * 这是一个固定模板，在企业中可以直接使用
     */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {

        //我们为了开发方便，一般直接使用<String,Object>类型
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        //设置连接工厂类
        redisTemplate.setConnectionFactory(redisConnectionFactory);


        //序列化配置
        //使用jaskson解析任意对象即将所有对象转为json
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //转译
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //新版本使用新方法 替换enableDefaultTyping
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator());
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key采用String的方式序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的方式序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //value采用jaskson方式序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
