package com.example.justlhyutils.service.time;

import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/14 16:37
 */
@Service(value = "timeCalculatorService")
public class TimeCalculatorServiceImpl implements TimeCalculatorService{

    @Override
    public String  getTimeCalculatorFactor(){
        return "a+b";
    }

}
