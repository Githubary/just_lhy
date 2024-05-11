package com.example.justlhyutils;

import com.example.justlhyutils.onebook.config.CorsConfig;
import com.example.justlhyutils.thread.TestThread;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author liuhuayang
 */
@Slf4j
@Import(CorsConfig.class)
@ImportResource("application-dubbo.xml")
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@MapperScan("com.example.justlhyutils.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        TestThread bean = ApplicationContextUtil.getBean(TestThread.class);
        bean.batchReleaseOrder();
        log.info("application start success");
    }


}
