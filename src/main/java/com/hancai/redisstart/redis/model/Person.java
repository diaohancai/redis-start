package com.hancai.redisstart.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author diaohancai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String id;

    private String name;

    private int age;

}
