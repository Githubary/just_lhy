package com.example.justlhyutils.redis;

import com.google.common.collect.Lists;
import org.redisson.api.RedissonClient;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/20 11:19
 */
public class RedissionTest {

    @Resource
    private RedissonClient redissonClient;


    public static void main(String[] args) {
        List<Integer> listasd = Lists.newArrayList(2,232,2123,23);
        List<Integer> listasd2 = Lists.newArrayList(1,2,2,3);
        for (Integer integer : listasd) {
            System.out.println(integer);
            for (Integer integer1 : listasd2) {
                System.out.println("j"+integer1);
                break;
            }
        }
        String aa = null;
        String bb = "22";
        if(Objects.equals(bb, aa)){
            System.out.println("asdsadassa");
        }
        List<List<String>> list = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < 15; i++) {
            if (j < 10) {
                j++;
            } else {
                list.add(subList);
                subList = new ArrayList<>();
                j = 0;
            }
            subList.add(String.valueOf(i));
            if(i==14){
                list.add(subList);
            }
        }
        System.out.println(list);
        String skuCode ="sda";
        String date = null;
        Boolean c = false;
        System.out.println(skuCode+date+c);
        int a = 2;
        byte b = 10;
        test();
    }

    public static void test() {
        AtomicInteger fishCount = new AtomicInteger();
        fishCount.getAndIncrement();
        System.out.println(fishCount.get());
    }
}
