package com.hancai.redisstart.redis.common;

import com.hancai.redisstart.RedisStartApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * RedisTemplate，redis 连接池测试
 *
 * @author diaohancai
 */
public class RedisTemplateTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testConnect() {
        Assert.notNull(redisTemplate, "redisTemplate 不能为空");

        Set<String> keys = redisTemplate.keys("*");
        for(String key : keys) {
            logger.info(key);
        }
    }

}
