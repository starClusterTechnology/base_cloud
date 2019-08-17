package com.base.redis.redisclient;



import com.base.redis.redisTemplate.JedisClusterConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

public final class RedisClientConfig {

    private RedisClientConfig(){

    }

    public final static Properties prop = JedisClusterConfig.prop;
    public final static JedisPool jedisPool;

    static {
        String nodes=prop.getProperty("plugin.redis.pool.node").trim();
        int timeout=Integer.parseInt(prop.getProperty("plugin.redis.pool.timeout").trim());
        int maxAttempts=Integer.parseInt(prop.getProperty("plugin.redis.pool.maxAttempts").trim());
        String[] ipPortPair = nodes.split(":");
        String ip = ipPortPair[0];
        int port = Integer.parseInt(ipPortPair[1]);

        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(maxAttempts);
        //设置超时时间
        config.setMaxWaitMillis(timeout);
        //初始化连接池
        jedisPool = new JedisPool(config, ip, port);

    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }


}
