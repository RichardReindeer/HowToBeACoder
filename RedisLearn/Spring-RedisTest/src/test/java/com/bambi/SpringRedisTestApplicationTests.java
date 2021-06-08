package com.bambi;

import com.bambi.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringRedisTestApplicationTests {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void contextLoads() throws JsonProcessingException {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        //如果直接向redis的key中传入未序列化的对象，会抛出异常，需要进行序列化翻译
        User bambi = new User().setAge(10).setName("bambi");
        String s = new ObjectMapper().writeValueAsString(bambi);
        redisTemplate.opsForValue().set("user1",s);
        System.out.println(redisTemplate.opsForValue().get("user1"));
    }

}
