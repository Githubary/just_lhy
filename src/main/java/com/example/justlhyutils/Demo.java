package com.example.justlhyutils;


import com.example.justlhyutils.config.ThreadPoolExecutorConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 10:36
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ThreadPoolTaskExecutor asyncExecutor = ApplicationContextUtil.getBeanByName("asyncExecutor", ThreadPoolTaskExecutor.class);
        System.out.println(asyncExecutor.getCorePoolSize());
        ThreadPoolExecutorConfiguration.adjustPool(asyncExecutor,4,4);
        log.info("{}",asyncExecutor.getCorePoolSize());
    }
}
