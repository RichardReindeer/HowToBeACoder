package com.bambi;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class TestPing {
    public static void main(String[] args) {
        //首先new一个Jedis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //jedis所有的命令就是之前学习的所有redis指令
        //所以之前的指令学习很重要
        String ping = jedis.ping();
        System.out.println("ping = " + ping);

        String set = jedis.set("key1", "这是第一个key");
        System.out.println(set);
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        String s = jedis.flushDB();
        System.out.println("s = " + s);
        jedis.lpush("key1","v1","key2","v2");
        List<String> key1 = jedis.lrange("key1", 0, -1);
        System.out.println("key1 = " + key1);
    }
}
