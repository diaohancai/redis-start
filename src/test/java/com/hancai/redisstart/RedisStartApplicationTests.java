package com.hancai.redisstart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisStartApplicationTests {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    /*
     * RedisTemplate 连接池
     */
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
    }

    @Test
    public void contextLoads() {
        Assert.assertNotNull(context);
        Assert.assertNotNull(mvc);
    }

}
