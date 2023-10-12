package com.example.justlhyutils.service.random.impl;

import com.example.justlhyutils.service.random.RandomService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 09:56
 */
@Service(value = "randomService")
public class RandomServiceImpl implements RandomService {

    @Override
    public double random01() {
        double randomValue = Math.random();
        System.out.println("随机数: " + randomValue);

        // 生成一个0到1之间的随机数，保留两位小数
        double roundedRandomValue = Math.round(randomValue * 100.0) / 100.0;
        System.out.println("保留两位小数的随机数: " + roundedRandomValue);
        return roundedRandomValue;
    }

    @Override
    public double safeRandom01() {
        Random random = new Random();

        // 生成0到1之间的随机数
        double randomValue = random.nextDouble();
        System.out.println("随机数: " + randomValue);

        // 生成一个在指定范围内的随机整数
        int randomInt = random.nextInt(100); // 生成0到99之间的随机整数
        System.out.println("随机整数: " + randomInt);
        return randomInt;
    }

    @Override
    public int random1x() {
        return 0;
    }
}
