package com.example.justlhyutils.design.createobject.original;

import com.alibaba.fastjson.JSON;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/24 22:41
 */
public class OriginalService {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        //比较小对象下用原型模式创建的速度
        compareSmallCreate();
        //比较大对象下用原型模式和new的创建速度
        compareBigCreate();
        //模拟输入一个数字，分割成多个货架。每个货架除了序号不同，初始化别的属性完全相同。
        inputOneDigital(100060);
    }

    private static void inputOneDigital(int i) {
        List<SmallModel> list =new ArrayList<>();
        SmallModel smallModel = new SmallModel();
        smallModel.setName("2");
        smallModel.setAge(10);
        smallModel.setNumber(20L);
        while(i>0){
            SmallModel clone = smallModel.clone();
            clone.setNumber((long) i);
            i=i-1000;
            list.add(clone);
        }
        System.out.println(JSON.toJSONString(list));
    }

    private static void compareBigCreate() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BigModel bigModel = new BigModel();
        System.out.println(bigModel);
        SmallModel smallModel3 = new SmallModel();
        smallModel3.setName("2");
        smallModel3.setAge(10);
        smallModel3.setNumber(20L);
        bigModel.setSmall(smallModel3);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            new BigModel();
        }
        System.out.println("使用new创建大对象的速度:"+(System.currentTimeMillis()-start1));
        long start2 = System.currentTimeMillis();
        SmallModel smallModel = new SmallModel();
        smallModel.setName(String.valueOf(0));
        smallModel.setAge(0);
        smallModel.setNumber(0L);
        for (int i = 0; i < 100000; i++) {
            BigModel clone = bigModel.clone();
            clone.setA4("234");
        }
        System.out.println("使用原型模式clone创建大对象的速度:"+(System.currentTimeMillis()-start2));
    }

    private static void compareSmallCreate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            String.valueOf(i);
        }
        System.out.println("String.valueOf的速度:"+(System.currentTimeMillis()-start));
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            SmallModel smallModel = new SmallModel();
            smallModel.setName("2");
            smallModel.setAge(i);
            smallModel.setNumber(0L);
        }
        System.out.println("使用new创建小对象的速度:"+(System.currentTimeMillis()-start1));
        long start2 = System.currentTimeMillis();
        SmallModel smallModel = new SmallModel();
        smallModel.setName(String.valueOf(0));
        smallModel.setAge(0);
        smallModel.setNumber(1000L*0);
        for (int i = 0; i < 100; i++) {
            SmallModel clone = smallModel.clone();
            clone.setAge(i);
        }
        System.out.println("使用原型模式clone创建小对象的速度:"+(System.currentTimeMillis()-start2));
    }

}
