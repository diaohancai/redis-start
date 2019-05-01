package com.hancai.redisstart.redis.datastructure;

import com.hancai.redisstart.RedisStartApplicationTests;
import com.hancai.redisstart.redis.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis string 类型测试
 *
 * @author diaohancai
 */
public class RedisStringTest extends RedisStartApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("redis_string_test", "hello world");

        String redis_test = (String) redisTemplate.opsForValue().get("redis_string_test");

        Assert.assertEquals("hello world", redis_test);
        logger.info(redis_test);
    }

    @Test
    public void testStringObject() {
        Person person = new Person("1", "diaohancai", 27);

        redisTemplate.opsForValue().set("redis_string_object_test", person);

        Person redisPerson = (Person) redisTemplate.opsForValue().get("redis_string_object_test");

        Assert.assertNotNull(redisPerson);
        logger.info(redisPerson.toString());
    }

}
