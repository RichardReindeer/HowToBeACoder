package com.bambi;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * 自己测试模仿用户向redis中存信息后查询好友距离
 */
public class MyTest01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        HashMap<String, GeoCoordinate> hashMap =  new HashMap<>();
        hashMap.put("user1",new GeoCoordinate(116.39712899923325,39.916526473629808));
        hashMap.put("user2",new GeoCoordinate(121.47,31.23));
        jedis.geoadd("users",hashMap);
        Double geodist = jedis.geodist("users", "user1", "user2");
        System.out.println("geodist = " + geodist);
    }
}
