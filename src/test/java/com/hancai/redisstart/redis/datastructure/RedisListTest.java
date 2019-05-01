package com.hancai.redisstart.redis.datastructure;

import com.hancai.redisstart.RedisStartApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * redis list 类型测试
 *
 * @author diaohancai
 */
public class RedisListTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testList() {
        Boolean exists = redisTemplate.hasKey("redis_list_test");
        if(exists) {
            redisTemplate.delete("redis_list_test");
        }

        redisTemplate.opsForList().rightPushAll("redis_list_test", "a", "b", "c", "d");
        List<Object> redis_list_test = redisTemplate.opsForList().range("redis_list_test", 0, -1);
        Assert.assertArrayEquals(new Object[]{"a", "b", "c", "d"}, redis_list_test.toArray());
        logger.info(redis_list_test.toString());

        redisTemplate.opsForList().leftPushAll("redis_list_test", 1, 2, 3);
        List<Object> redis_list_test2 = redisTemplate.opsForList().range("redis_list_test", 0, -1);
        Assert.assertArrayEquals(new Object[]{3, 2, 1, "a", "b", "c", "d"}, redis_list_test2.toArray());
        logger.info(redis_list_test2.toString());

        Object rightPop = redisTemplate.opsForList().rightPop("redis_list_test");
        Assert.assertEquals("d", rightPop);

        Object leftPop = redisTemplate.opsForList().leftPop("redis_list_test");
        Assert.assertEquals(3, leftPop);
    }

}
