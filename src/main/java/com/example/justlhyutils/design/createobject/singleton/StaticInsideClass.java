package com.example.justlhyutils.design.createobject.singleton;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/27 15:15
 */
public class StaticInsideClass {

    private StaticInsideClass() {
    }

    private static class StaticClass{
        private static final StaticInsideClass SINGLETON = new StaticInsideClass();
    }

    public static StaticInsideClass getInstance(){
        return StaticClass.SINGLETON;
    }

}
