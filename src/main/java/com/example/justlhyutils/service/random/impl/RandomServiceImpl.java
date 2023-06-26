package com.example.justlhyutils.service.random.impl;

import com.example.justlhyutils.service.random.RandomService;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 09:56
 */
public class RandomServiceImpl implements RandomService {

    @Override
    public BigDecimal random01() {
        return new BigDecimal(2);
    }

    @Override
    public int random1x() {
        return 0;
    }
}
