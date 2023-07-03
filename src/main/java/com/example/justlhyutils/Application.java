package com.example.justlhyutils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/**
 * @author liuhuayang
 */
@Slf4j
@ImportResource("application-dubbo.xml")
@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        log.info("application start success");
    }


}
