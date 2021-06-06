package com.bambi;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("key1","v1");
        hashMap.put("key2","v2");
        hashMap.put("key3","v3");
        hashMap.put("key4","v4");

        //创建一个名为hash的key
        jedis.flushDB();
        String hash = jedis.hmset("hash",hashMap);
        Map<String, String> hash1 = jedis.hgetAll("hash");
        System.out.println("hash1 = " + hash1);


    }
}
