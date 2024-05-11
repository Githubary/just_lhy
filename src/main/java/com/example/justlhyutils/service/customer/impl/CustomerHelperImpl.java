package com.example.justlhyutils.service.customer.impl;

import com.example.justlhyutils.mapper.model.CustomerData;
import com.example.justlhyutils.mapper.CustomerDataMapper;
import com.example.justlhyutils.service.customer.CustomerHelper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/26 09:09
 */
@CacheConfig
@Service(value = "customerHelper")
public class CustomerHelperImpl implements CustomerHelper {

    private final CustomerDataMapper customerDataMapper;

    public CustomerHelperImpl(CustomerDataMapper customerDataMapper) {
        this.customerDataMapper = customerDataMapper;
    }

    @Cacheable(cacheNames = "getCustomerById",key = "'id'+#id")
    @Override
    public CustomerData getCustomerById(Long id) {
        return customerDataMapper.selectByPrimaryKey(id);
    }

}
