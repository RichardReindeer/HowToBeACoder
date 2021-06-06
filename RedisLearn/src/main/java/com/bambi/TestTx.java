package com.bambi;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 使用jedis测试事务
 */
public class TestTx {
    public static void main(String[] args) {
        //Jedis jedis = new Jedis("12.0.0.1",6379);
        Jedis jedis1 = new Jedis();

        //创建一个Json对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user1","bambi");
        jsonObject.put("user2","java");

        //开启事务
        Transaction multi = jedis1.multi();
        //将json对象转为String
        try {
            jedis1.watch("key1");
            String s = jsonObject.toString();
            multi.set("key1",s);
            int i = 1/0;
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //如果执行失败则放弃事务!!!!!!!!!!!!!!!!!!
            multi.discard();
        }finally {
            System.out.println(jedis1.get("key1"));
            jedis1.close();
        }
    }
}
