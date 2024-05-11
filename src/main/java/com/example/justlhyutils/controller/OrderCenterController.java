package com.example.justlhyutils.controller;

import com.alibaba.fastjson2.JSON;
import com.example.justlhyutils.mapper.model.CustomerData;
import com.example.justlhyutils.model.Customer;
import com.example.justlhyutils.onebook.model.WebResult;
import com.example.justlhyutils.service.customer.CustomerHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/25 10:23
 */
@Slf4j
@RequestMapping(value = "/order")
@RestController
public class OrderCenterController {

    private final CustomerHelper customerHelper;

    public OrderCenterController(CustomerHelper customerHelper) {
        this.customerHelper = customerHelper;
    }

    @GetMapping(value = "/list")
    public WebResult<List<Customer>> listCustomer(@RequestParam(value = "id") Long id) {
        CustomerData customerData = customerHelper.getCustomerById(id);
        log.info("customerData:{}", JSON.toJSONString(customerData));
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerData, customer);
        List<Customer> list = Lists.newArrayList(customer);
        return WebResult.success(list);
    }

}
