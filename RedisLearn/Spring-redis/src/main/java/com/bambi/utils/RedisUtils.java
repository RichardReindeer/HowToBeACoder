package com.bambi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * RedisUtils工具类
 *
 * @author Bambi
 */

@Component
public class RedisUtils {

    @Autowired
    //因为不指定方法名，老是找不到自己注入的自定义RedisTemplate。。。。。
    @Qualifier("getRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate;

    //------------------------------------------------------------

    /**
     * 指定缓存过期时间
     *
     * @param key  redis的key值
     * @param time 想要保存的时间
     * @return 设置成功则返回true
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取该key的保存过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    /**
     * 判断库中是否存在该键值
     *
     * @param key 想去判断的键值
     * @return 没啥可说的
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void del(String... key) {
        if (key.length == 1) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
        }
    }

    /**
     * 根据key获取value
     *
     * @param key 想要查修value的key值
     * @return 这里用了一个三木运算，有点小优秀
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置普通的redis键值对
     *
     * @param key   redis的key值
     * @param value redis的value值
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置存活时间
     *
     * @param key   需要放入缓存中的key
     * @param value 需要放入key中的 值
     * @param time  需要设置的存活时间，如果时间长度小于0，则视为无时间限制
     * @return
     */
    public boolean set(String key, Object value, Long time) {
        try {
            if (time < 0) {
                set(key, value);
            } else {
                set(key, value, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 使key的值进行自增的方法
     *
     * @param key   想要自增value的key
     * @param delta 自增的增长因子
     * @return 增长完之后多大
     */
    public Long incr(String key, Long delta) {
        if (delta < 0) {
            throw new RuntimeException("增长因子必须大于0");
        }
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        return increment;
    }

    /**
     * 使key值自减的方法
     *
     * @param key   需要进行这个操作的key
     * @param delta 自减的自减因子
     * @return
     */
    public Long decr(String key, Long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 获取hash中的值
     *
     * @param key  不能为空
     * @param item 不能为空
     */
    public void hget(String key, String item) {
        redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 批量导入hash 的value值
     *
     * @param key 不能为空
     * @param map 应该对应多个键值对
     * @return 成功就是true
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 给放入的key 一起设置存活时间
     *
     * @param key  就是个key
     * @param map  多个键值对
     * @param time 存活时间
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, Long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通的向hashkey中赋值
     *
     * @param key
     * @param item
     * @param obj
     * @return
     */
    public boolean hset(String key, String item, Object obj) {
        try {
            redisTemplate.opsForHash().put(key, item, obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向缓存中添加hash的时候顺便给这个key赋予存活时间
     *
     * @param key   键
     * @param item  hash的名字
     * @param value hash对应的值
     * @param time  存活的时间
     * @return
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除表中的值
     * @param key 存入缓存中的键，不能为空
     * @param hashKeys 可以是多个，但是不能为欸空
     */
    public Long hdel(String key , Object... hashKeys){
        Long delete = redisTemplate.opsForHash().delete(key, hashKeys);
        return delete;
    }

    /**
     * 判断key中是否存在该值
     * @param key
     * @param hashKeys
     * @return
     */
    public boolean hHasKey(String key , Object hashKeys){
        return redisTemplate.opsForHash().hasKey(key, hashKeys);
    }

    /**
     * hash的自增
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double incr(String key , String item , double by){
        return redisTemplate.opsForHash().increment(key,item,by);
    }

    /**
     * hash的自减
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double decr(String key ,String item , double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    /**
     * 返回这个set中的所有值
     * @param key
     * @return
     */
    public Set<Object> sGet(String key){
        try {
            return  redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sHashKey(String key , Object member){
        try {
            return redisTemplate.opsForSet().isMember(key,member);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向set集合中添加值
     * @param key
     * @param value 可以是多个值
     * @return 返回添加成功的个数
     */
    public Long sAdd(String key , Object... value){
        try {
           return  redisTemplate.opsForSet().add(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在给set赋值的时候添加存活时间
     * @param key
     * @param time
     * @param value
     */
    public long sSetAndTime(String key , Long time , Object... value){
        try {
            Long add = redisTemplate.opsForSet().add(key, value);
            if(time>0){
                expire(key,time);
            }
            return add;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取Set缓存的长度
     * @param key
     * @return
     */
    public long sGetSetSize(String key){
        try {
            Long size = redisTemplate.opsForSet().size(key);
            return size;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的hash元素
     * @param key 键
     * @param value 可以为多个
     * @return
     */
    public long setRemove(String key , Object... value){
        try {
            Long remove = redisTemplate.opsForSet().remove(key, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================
    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
