package com.example.justlhyutils;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/15 11:05
 */

import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DubboInterfacePrinter implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, ServiceBean> serviceBeans = applicationContext.getBeansOfType(ServiceBean.class);
        for (ServiceBean serviceBean : serviceBeans.values()) {
            String interfaceName = serviceBean.getInterface();
            System.out.println("Registered interface: " + interfaceName);
        }
    }
}

