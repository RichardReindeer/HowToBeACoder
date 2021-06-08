package com.bambi;

import com.bambi.pojo.User;
import com.bambi.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.UnknownHostException;

@SpringBootTest
class SpringRedisApplicationTests {

    @Autowired
    @Qualifier("getRedisTemplate")
    private RedisTemplate redisTemplate;


    //如果想要使用工具类
    @Resource
    private RedisUtils redisUtils;

    SpringRedisApplicationTests() throws UnknownHostException {
    }

    @Test
    void contextLoads() {

        //redisTemplate.opfForValue()  则是操作字符串
        //redisTemplate.opfForList()  则是操作链表
        //redisTemplate.opfFor....    就是学过的数据类型

        //除了对基本类型的操作，常用的命令都可以通过redisTemplate直接调用
        //不用去启动类自己注入，它自己有auto注入
        redisTemplate.multi();
        //获取redis连接对象
        /*RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();*/
        redisTemplate.opsForValue().set("key1","value1");
        Object key1 = redisTemplate.opsForValue().get("key1");
        System.out.println(key1.toString());
    }

    @Test
    public void User() throws JsonProcessingException {
        //真实的开发中一般都使用json来传递对象
        User user = new User(1, "bambi", "admin");
        System.out.println("将user类对象转换为json对象");
        String s = new ObjectMapper().writeValueAsString(user);
        System.out.println("s = " + s);
        redisTemplate.opsForValue().set("user",s);
        Object user1 = redisTemplate.opsForValue().get("user");
        System.out.println(user1);
    }

    @Test
    public void utils(){
        boolean set = redisUtils.set("name", "bambi");
        System.out.println(redisUtils.get("name"));
    }
}
