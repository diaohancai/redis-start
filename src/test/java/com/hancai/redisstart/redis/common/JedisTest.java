package com.hancai.redisstart.redis.common;

import com.hancai.redisstart.RedisStartApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Jedis 测试
 *
 * @author diaohancai
 */
public class JedisTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static String HOST = "192.168.199.3";
    private static int PORT = 6379;
    private static String PASSWORD = "11111111";

    /**
     * 测试连接状态
     */
    @Test
    public void testPing() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(HOST, PORT);
            Assert.assertNotNull(jedis);
            jedis.auth(PASSWORD);

            String result = jedis.ping();
            logger.info(result);

            Assert.assertEquals("PONG", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close(); // 关闭 redis 连接
            }
        }
    }

    /**
     * 测试获取所有 key
     */
    @Test
    public void testGetKeysAll() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(HOST, PORT);
            Assert.assertNotNull(jedis);
            jedis.auth(PASSWORD);

            Set<String> keys = jedis.keys("*");
            for(String key : keys) {
                logger.info(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close(); // 关闭 redis 连接
            }
        }
    }

}
