package com.example.justlhyutils.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/2/28 15:05
 */
@Slf4j
public class ExceptionClass {
    public static void main(String[] args) {
        try{
            method1();
        }catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getLocalizedMessage());
            System.out.println(Arrays.toString(e.getSuppressed()));
            System.out.println(getRealMessage(e));
        }
    }

    private static void method1() {
        method2();
    }

    private static void method2() {
        int a = 0;
        int i = 1 / a;
    }

    public static String getRealMessage(Throwable e) {
        System.out.println("e:"+JSON.toJSONString(e));
        // 如果e不为空，则去掉外层的异常包装
        while (e != null) {
            Throwable cause = e.getCause();
            if (cause == null) {
                System.out.println("e.getMessage:" + e.getMessage());
                return e.getMessage();
            }
            System.out.println("cause:"+JSON.toJSONString(cause));
            e = cause;
        }
        return "";
    }
}
