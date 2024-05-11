package com.example.justlhyutils.thread;

import java.util.concurrent.Callable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/1/11 15:09
 */
public class CallableWrapper<V> implements Callable<V> {
    final Callable<V> callable;

    public static <V> CallableWrapper<V> of(Callable<V> r) {
        return new CallableWrapper(r);
    }

    public CallableWrapper(Callable<V> callable) {
        this.callable = callable;
    }

    public V call() throws Exception {
        return this.callable.call();
    }
}
