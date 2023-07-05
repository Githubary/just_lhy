package com.example.justlhyutils.feign;

import com.example.justlhyutils.model.FeignRequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/19 09:52
 */
@RequestMapping("feign")
public interface GeneralFeignService {
    @PostMapping({"/get"})
    String getSomething(@RequestBody FeignRequestBody requestBody);
}
