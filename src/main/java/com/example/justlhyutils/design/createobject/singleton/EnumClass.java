package com.example.justlhyutils.design.createobject.singleton;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/27 15:05
 */
public enum EnumClass {

    INSTANCE;
    private final Single single;

    EnumClass(){
        single = new Single();
    }

    public Single getInstance(){
        return single;
    }

    static class Single {

    }

}
