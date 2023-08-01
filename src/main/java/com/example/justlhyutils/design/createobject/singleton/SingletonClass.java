package com.example.justlhyutils.design.createobject.singleton;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/27 14:49
 */
public class SingletonClass {

    public static void main(String[] args) {
        EnumClass.Single instance = EnumClass.INSTANCE.getInstance();
    }

    /**
     * 单例模式，全局唯一，无法通过任何方式构建。
     * 分为懒汉式和饿汉式。
     */
    private SingletonClass() {

    }

    private static volatile SingletonClass singleton;

    public static SingletonClass getInstance(){
        if (singleton == null) {
            synchronized (SingletonClass.class) {
                if (singleton == null) {
                    singleton =  new SingletonClass();
                    return singleton;
                }
                return singleton;
            }
        }
        return singleton;
    }


}
