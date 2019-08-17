package com.base.redis.redisTemplate;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class JedisClusterConfig {

    public static Properties prop;

    private static JedisCluster jedisCluster = null;

    static{
        prop =  new Properties();
        InputStream in = JedisClusterConfig.class.getResourceAsStream("/redis.properties" );
        try {
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JedisCluster getJedisCluster(){
        if(jedisCluster == null) {
            synchronized (JedisClusterConfig.class) {
                if (jedisCluster == null) {
                    String nodes = prop.getProperty("plugin.redis.pool.nodes").trim();
                    int timeout = Integer.valueOf(prop.getProperty("plugin.redis.pool.timeout").trim());
                    int maxAttempts = Integer.valueOf(prop.getProperty("plugin.redis.pool.maxAttempts").trim());
                    String[] serverArray = nodes.split(",");//获取服务器数组
                    Set<HostAndPort> hostAndPort = new HashSet<>();
                    for (String ipPort : serverArray) {
                        String[] ipPortPair = ipPort.split(":");
                        hostAndPort.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
                    }
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(50);
                    poolConfig.setMaxIdle(10);
                    jedisCluster = new JedisCluster(hostAndPort, timeout, maxAttempts,poolConfig);
                }
            }
        }
        return jedisCluster;
    }
}
