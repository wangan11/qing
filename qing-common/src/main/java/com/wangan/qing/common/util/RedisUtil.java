package com.wangan.qing.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);
    private static String IP=PropertiesFileUtil.getInstance("redis").get("master.redis.ip");
    private static int PORT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.port");

    // 访问密码
    private static String PASSWORD = AESUtil.aesDecode(PropertiesFileUtil.getInstance("redis").get("master.redis.password"));
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active");

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_idle");

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_wait");

    // 超时时间
    private static int TIMEOUT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.timeout");
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;

    private static JedisPool jedisPool = null;

    private static void initialPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
    }

    private static synchronized void poolInit(){
        if(null==jedisPool){
            initialPool();
        }
    }

    private static synchronized Jedis getJedis(){
        poolInit();
        Jedis jedis = null;
        if(null!=jedisPool){
            jedis = jedisPool.getResource();
            try {
                jedis.auth(PASSWORD);
            } catch (Exception e) {
                LOGGER.error("Get jedis error : " + e);
            }
        }
        return jedis;
    }
}
