package com.example.justlhyutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuhuayang
 */
@ImportResource("application-dubbo.xml")
@SpringBootApplication
public class JustlhyUtilsApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JustlhyUtilsApplication.class, args);
        new CountDownLatch(1).await();
    }


}
