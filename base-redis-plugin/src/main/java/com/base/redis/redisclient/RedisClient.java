package com.base.redis.redisclient;



import com.base.common.utils.CollectionUtil;
import com.base.common.utils.JsonUtil;
import com.base.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.List;

public class RedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);


    private Jedis jedis =RedisClientConfig.getJedis();

    private static final String redisUtilset = "RedisUtil:set cache key={},value={}";
    private static final String redisUtilsetWithExpireTime = "RedisUtil:setWithExpireTime cache key={},value={},expireTime={}";
    private static final String redisUtildelete = "RedisUtil:delete cache key={}";

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    /**
     * 设置缓存
     *
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key    缓存key
     * @param value  缓存value
     */
    public void set(String prefix, String key, String value) {
        //System.out.println(jedisCluster + "999999999999999999999999");
        jedis.set(prefix + KEY_SPLIT + key, value);
        LOGGER.debug(redisUtilset, prefix + KEY_SPLIT + key, value);
    }


    /**
     * 设置缓存并且设置超时时间
     *
     * @param prefix  缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key     缓存key
     * @param object  缓存object
     * @param seconds 超时时间seconds
     */
    public void set(String prefix, String key, Serializable object, Integer seconds) {
        String redisKey = prefix + KEY_SPLIT + key;
        jedis.setex(redisKey.getBytes(), seconds, serialize(object));
        //jedisCluster.expire(redisKey,seconds);
    }

    /**
     * 获取redis对象
     *
     * @param prefix  缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key     缓存key
     *
     */
    public Object getObj(String prefix, String key) {
        String redisKey = prefix + KEY_SPLIT + key;
        byte[] bytes = jedis.get(redisKey.getBytes());
        if (bytes == null) {
            return null;
        }
        return toObject(bytes);
    }

    /**
     * 设置过期时间，和跟新过期时间
     *
     * @param prefix
     * @param key
     * @param time
     */
    public void setTime(String prefix, String key, int time) {
        jedis.expire(prefix + KEY_SPLIT + key, time);
        LOGGER.debug(redisUtilset, prefix + KEY_SPLIT + key, time);
    }

    public void setTime(String key,int seconds){
        jedis.expire(key,seconds);
    }


    /**
     * 设置缓存，并且自己指定过期时间
     *
     * @param prefix
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime(String prefix, String key, String value, int expireTime) {
        jedis.setex(prefix + KEY_SPLIT + key, expireTime, value);
        LOGGER.debug(redisUtilsetWithExpireTime, prefix + KEY_SPLIT + key, value,
                expireTime);
    }

    public void setWithExpireTime(String key,String value,int expireTime){
        jedis.setex( key, expireTime, value);
        LOGGER.debug(redisUtilsetWithExpireTime, key, value,
                expireTime);
    }

    /**
     * 获取指定key的缓存
     *
     * @param prefix
     * @param key
     */
    public String get(String prefix, String key) {
        String keyStr = prefix + KEY_SPLIT + key;
        String value = jedis.get(keyStr);
        LOGGER.debug("RedisUtil:get cache key={},value={}", keyStr, value);
        return value;
    }

    /**
     * 获取key缓存
     * @param key
     * @return
     */
    public String get(String key) {
        String value = jedis.get(key);
        LOGGER.debug("RedisUtil:get cache key={}", value);
        return value;
    }

    public boolean exists(String key){
        return StringUtil.isNotEmpty(get(key));
    }

    /**
     * 删除指定key的缓存
     *
     * @param prefix
     * @param key
     */
    public void deleteWithPrefix(String prefix, String key) {
        jedis.del(prefix + KEY_SPLIT + key);
        LOGGER.debug(redisUtildelete, prefix + KEY_SPLIT + key);
    }

    public void delete(String key) {
        jedis.del(key);
        LOGGER.debug(redisUtildelete, key);
    }

    /**
     * 求交集
     *
     * @param keys 交集的key值
     */
    public void sinter(String... keys) {
        jedis.sinter(keys);
        LOGGER.debug(redisUtilset, keys);
    }

    /**
     * 求并集
     *
     * @param keys 并集的key值
     */
    public void sunion(String... keys) {
        jedis.sunion(keys);
        LOGGER.debug(redisUtilset, keys);
    }

    public void ObjectInRedis(String key, List<?> lists,int expireTime){
        if(CollectionUtil.isNotEmpty(lists)){
             setWithExpireTime(key, JsonUtil.bean2Json(lists),expireTime);
        }
    }
    public void ObjectInRedis(String key, Object obj,int expireTime){
        if(obj != null){
            setWithExpireTime(key, JsonUtil.bean2Json(obj),expireTime);
        }
    }

    private static byte[] serialize(Serializable object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] bytes = null;
        try {
            // 序列化
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            LOGGER.error("exception",e);
        }

        return bytes;
    }

    private static Object toObject(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        // 反序列化
        Object obj = null;
        try {
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException e) {
            LOGGER.error("exception",e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("exception",e);
        }
        return obj;
    }

}
