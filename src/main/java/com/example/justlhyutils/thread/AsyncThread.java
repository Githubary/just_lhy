package com.example.justlhyutils.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/26 10:14
 */
@Component
public class AsyncThread {

    private final ThreadPoolTaskExecutor asyncTaskExecutor;

    public AsyncThread(ThreadPoolTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public void doSomethingAsync() {
        for (int i = 0; i < 10; i++) {
            asyncTaskExecutor.submit(new MyAsyncTask());
        }
    }

    static class MyAsyncTask implements Runnable{
        @Override
        public void run() {
            File file = new File("classpath:file/batchReadAsync.txt");
        }
    }

}
