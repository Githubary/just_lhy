//package com.example.justlhyutils.config;
//
//import com.example.justlhyutils.feign.GeneralFeignService;
//import feign.Feign;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * description:
// *
// * @author liuhuayang
// * date: 2023/7/3 18:34
// */
//@Configuration
//public class FeignConfiguration {
//    @Bean
//    public GeneralFeignService generalFeignService() {
//        return Feign.builder()
//                .target(GeneralFeignService.class, "http://localhost:8080/");
//    }
//}
//
