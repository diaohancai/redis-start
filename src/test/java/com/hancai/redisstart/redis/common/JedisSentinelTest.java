package com.hancai.redisstart.redis.common;

import com.hancai.redisstart.RedisStartApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis sentinel 哨兵机制，实现高可用
 *
 * @author diaohancai
 */
public class JedisSentinelTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * mater name
     */
    private final static String MASTERNAME = "mymaster";

    /**
     * sentinels
     */
    private final static Set<String> SENTINELS = new HashSet<String>(){{
        add("192.168.199.3:26379");
        add("192.168.199.3:26380");
        add("192.168.199.3:26381");
    }};

    private final static String MASTER_PASSWORD = "11111111";

    private final static String HEARTBEAT_KEY = "_heartbeat";

    private final static String HEARTBEAT_VALUE = "pong";

    /**
     *  测试 redis sentinel 哨兵机制
     */
    @Test
    public void testJedisSentinel() {
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(MASTERNAME, SENTINELS);
        Random random = new Random();

        while (true) {
            Jedis jedis = null;
            try {
                jedis = jedisSentinelPool.getResource();
                jedis.auth(MASTER_PASSWORD);
                jedis.set(HEARTBEAT_KEY, HEARTBEAT_VALUE + "_" + random.nextInt(10000));

                logger.info("{} value is {}", HEARTBEAT_KEY, jedis.get(HEARTBEAT_KEY));

                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                //logger.error(e.getMessage());
                e.printStackTrace();
            } finally {
                if(jedis != null) {
                    jedis.close();
                }
            }
        }
    }

}
