package com.example.justlhyutils.redis;


import com.example.justlhyutils.Application;
import com.example.justlhyutils.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/7 10:46
 */
public class SpringRedisDemo {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        RedisTemplate<String,Object> redisTemplate = ApplicationContextUtil.getBeanByName("myRedisTemplate",RedisTemplate.class);
//        operateValue(redisTemplate);
//        operateList(redisTemplate);
        operateHashMap(redisTemplate);
//        operateSet(redisTemplate);
//        operateZset(redisTemplate);
    }

    private static void operateZset(RedisTemplate<String,Object> redisTemplate) {

    }

    private static void operateSet(RedisTemplate<String,Object> redisTemplate) {

    }

    private static void operateHashMap(RedisTemplate<String,Object> redisTemplate) {
        RedisConnection connection = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection();
        Properties memory = connection.info("memory");
        System.out.println(memory);
        assert memory != null;
        System.out.println(memory.containsKey("migrate_cached_sockets"));
        redisTemplate.opsForHash().put("3","","");
        RedisOperations<String,?> operations = redisTemplate.opsForHash().getOperations();
    }

    private static void operateList(RedisTemplate<String,Object> redisTemplate) {
        redisTemplate.delete("3");
        List<Object> range = redisTemplate.opsForList().range("3", 0, -1);
        System.out.println(range);
        redisTemplate.opsForList().leftPush("3",4);
        redisTemplate.opsForList().leftPush("2",3);
        Object o = redisTemplate.opsForList().leftPop("3");
        Object o1 = redisTemplate.opsForList().leftPop("2");
        System.out.println(o); //4
        System.out.println(o1); //3
        List<Object> range1 = redisTemplate.opsForList().range("3", 0, -1);
        System.out.println(range1);
        redisTemplate.opsForList().rightPush("3",1);
        redisTemplate.opsForList().rightPush("3",2);
        redisTemplate.opsForList().rightPush("3",3);
        redisTemplate.opsForList().rightPush("3",4);
        redisTemplate.opsForList().rightPush("3",5);
        List<Object> range2 = redisTemplate.opsForList().range("3", 0, -1);
        System.out.println(range2);
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        listOperations.set("3",4,9);
        List<Object> range3 = redisTemplate.opsForList().range("3", 0, -1);
        System.out.println(range3);
    }

    private static void operateValue(RedisTemplate<String,Object> redisTemplate) {
        redisTemplate.opsForValue().set("张三",13);
    }
}
