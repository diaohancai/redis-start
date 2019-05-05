package com.hancai.redisstart.redis.func;

import com.hancai.redisstart.RedisStartApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Set;

/**
 * redis pipeline 管道功能
 *
 * @author diaohancai
 */
public class RedisPipelineTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * M 操作与 pipeline 的区别：
     * M 操作：原子性
     * pipeline：非原子性
     */
    @Test
    public void testPipeline() {
        // redis pipeline 管道操作
        redisTemplate.executePipelined((RedisConnection connection) -> {
            for (int i = 0; i < 10; ++i) {
                connection.set(
                        redisTemplate.getStringSerializer().serialize("pipeline_test_" + i),
                        redisTemplate.getStringSerializer().serialize(String.valueOf(i))
                );
            }
            return null;
        });

        Set<String> keys = redisTemplate.keys("pipeline_test_?");
        Assert.assertTrue(keys!=null && keys.size()==10);
        for(String key : keys) {
            logger.info(key);
        }

        Object pipeline9 = redisTemplate.opsForValue().get("pipeline_test_9");
        Assert.assertEquals(9, pipeline9);
    }

}
