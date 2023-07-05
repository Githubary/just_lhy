package com.example.justlhyutils.feign.impl;

import com.example.justlhyutils.feign.GeneralFeignService;
import com.example.justlhyutils.model.FeignRequestBody;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/19 09:52
 */
public class GeneralFeignServiceImpl implements GeneralFeignService {

    @Override
    public String getSomething(FeignRequestBody requestBody) {
        return "成功使用feign";
    }
}
