package com.base.redis.redisTemplate;


import com.base.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

import java.io.*;


public class RedisTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplate.class);


    private static JedisCluster jedisCluster = JedisClusterConfig.getJedisCluster();


    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    /**
     * 设置缓存
     *
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key    缓存key
     * @param value  缓存value
     */
    public static void set(String prefix, String key, String value) {
        System.out.println(jedisCluster + "999999999999999999999999");
        jedisCluster.set(prefix + KEY_SPLIT + key, value);
        LOGGER.debug("RedisUtil:set cache key={},value={}", prefix + KEY_SPLIT + key, value);
    }


    /**
     * 设置缓存并且设置超时时间
     *
     * @param prefix  缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key     缓存key
     * @param object  缓存object
     * @param seconds 超时时间seconds
     */
    public static void set(String prefix, String key, Serializable object, Integer seconds) {
        String redisKey = prefix + KEY_SPLIT + key;
        set(redisKey,object,seconds);
    }

    /**
     * 设置缓存并且设置超时时间
     * @param key     缓存key
     * @param object  缓存object
     * @param seconds 超时时间seconds
     */
    public static void set(String key, Serializable object, Integer seconds) {
        jedisCluster.setex(key.getBytes(), seconds, serialize(object));
    }

    /**
     * 获取redis对象
     *
     * @param prefix  缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key     缓存key
     *
     */
    public static Object getObj(String prefix, String key) {
        String redisKey = prefix + KEY_SPLIT + key;
        byte[] bytes = jedisCluster.get(redisKey.getBytes());
        return toObject(bytes);
    }

    /**
     * 设置过期时间，和跟新过期时间
     *
     * @param prefix
     * @param key
     * @param time
     */
    public static void setTime(String prefix, String key, int time) {
        jedisCluster.expire(prefix + KEY_SPLIT + key, time);
        LOGGER.debug("RedisUtil:set cache key={},value={}", prefix + KEY_SPLIT + key, time);
    }


    /**
     * 设置缓存，并且自己指定过期时间
     *
     * @param prefix
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public static void setWithExpireTime(String prefix, String key, String value, int expireTime) {
        jedisCluster.setex(prefix + KEY_SPLIT + key, expireTime, value);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value,
                expireTime);
    }

    /**
     * 设置缓存，并且由配置文件指定过期时间
     *
     * @param prefix
     * @param key
     * @param value
     */
    public static void setWithExpireTime(String prefix, String key, String value) {
//       // int EXPIRE_SECONDS = redisConnection.getTimeout();
//        jedisCluster.setex(prefix + KEY_SPLIT + key, EXPIRE_SECONDS, value);
//        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value,
//                EXPIRE_SECONDS);
    }

    /**
     * 获取指定key的缓存
     *
     * @param prefix
     * @param key
     */
    public static String get(String prefix, String key) {
        String value = jedisCluster.get(prefix + KEY_SPLIT + key);
        LOGGER.debug("RedisUtil:get cache key={},value={}", prefix + KEY_SPLIT + key, value);
        return value;
    }

    /**
     * 删除指定key的缓存
     *
     * @param prefix
     * @param key
     */
    public static void deleteWithPrefix(String prefix, String key) {
        jedisCluster.del(prefix + KEY_SPLIT + key);
        LOGGER.debug("RedisUtil:delete cache key={}", prefix + KEY_SPLIT + key);
    }

    public static void delete(String key) {
        jedisCluster.del(key);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }

    /**
     * 求交集
     *
     * @param keys 交集的key值
     */
    public static void sinter(String... keys) {
        jedisCluster.sinter(keys);
        LOGGER.debug("RedisUtil:set cache key={},value={}", keys);
    }

    /**
     * 求并集
     *
     * @param keys 并集的key值
     */
    public static void sunion(String... keys) {
        jedisCluster.sunion(keys);
        LOGGER.debug("RedisUtil:set cache key={},value={}", keys);
    }

    private static byte[] serialize(Serializable object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object toObject(byte[] bytes) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 反序列化
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            bis.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean exists(String prefix,String key){
        return StringUtil.isNotEmpty(get(prefix,key));
    }

}
