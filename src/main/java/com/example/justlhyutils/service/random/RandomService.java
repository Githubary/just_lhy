package com.example.justlhyutils.service.random;

import java.math.BigDecimal;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 09:56
 */
public interface RandomService {

    /**
     * 0到1的随机小数
     * @return
     */
    BigDecimal random01();

    /**
     * 1到某个数的随机整数
     * @return
     */
    int random1x();

}
