package com.example.justlhyutils.feign.impl;

import com.example.justlhyutils.feign.GeneralFeignService;
import com.example.justlhyutils.model.FeignRequestBody;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/19 09:52
 */
@Component
public class GeneralFeignServiceImpl implements GeneralFeignService {

    private final RedisTemplate myRedisTemplate;

    public GeneralFeignServiceImpl(RedisTemplate myRedisTemplate) {
        this.myRedisTemplate = myRedisTemplate;
    }

    @Override
    public String getSomething(FeignRequestBody requestBody) {
        return "成功使用feign";
    }
}
