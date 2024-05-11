package com.example.justlhyutils.model;

import com.alibaba.fastjson2.JSON;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/25 10:24
 */
public class Customer implements Serializable {


    public static void main(String[] args) {
        List<Customer> list = new ArrayList<>();
        Customer customer = new Customer();
        customer.setName("asda222");
        Customer customer2 = new Customer();
        customer2.setName(" ");
        list.add(customer);
        list.add(customer2);
        System.out.println(JSON.toJSONString(list));
        Optional<Customer> first = list.stream().filter(n -> StringUtils.isNotBlank(n.getName())).findFirst();
        first.ifPresent(value -> System.out.println(value.getName()));
        String[] lotNo = list.stream().map(Customer::getName).filter(StringUtils::isNotBlank).collect(Collectors.toList()).toArray(new String[]{});
        System.out.println(JSON.toJSONString(lotNo));
    }

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int index;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Customer) {
            Customer userInfo = (Customer) obj;
            return ObjectUtils.nullSafeEquals(this.name,userInfo.getName());
        }
        return false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
