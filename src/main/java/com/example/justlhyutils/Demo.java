package com.example.justlhyutils;


import com.example.justlhyutils.config.ThreadPoolExecutorConfiguration;
import com.google.common.collect.Lists;
import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 10:36
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        String oburl = "https://m.yonghuivip.com/yh-m-site/yh-out-invoice/index.html?offflag=0&code={0}&type={2}&_k={1}";
        String ererere = MessageFormat.format(oburl, "2xxxxxxxxxxx2", 3, "ererere");
        System.out.println(ererere);
        List<Integer> integers = Lists.newArrayList(0);
        System.out.println(integers);
//        SpringApplication.run(Application.class, args);
//        ThreadPoolTaskExecutor asyncExecutor = ApplicationContextUtil.getBeanByName("asyncExecutor", ThreadPoolTaskExecutor.class);
//        System.out.println(asyncExecutor.getCorePoolSize());
//        ThreadPoolExecutorConfiguration.adjustPool(asyncExecutor,4,4);
//        log.info("{}",asyncExecutor.getCorePoolSize());
    }
}
