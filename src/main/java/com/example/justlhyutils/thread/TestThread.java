package com.example.justlhyutils.thread;

import com.example.justlhyutils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/3/28 18:00
 */
@Slf4j
@Component
public class TestThread {

    private final ThreadPoolTaskExecutor asyncTaskExecutor;

    public TestThread(ThreadPoolTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    public void batchReleaseOrder() throws InterruptedException {
        AtomicLong count = new AtomicLong(2);
        for (int i = 0; i <4; i++) {
            Thread.sleep(100);
            asyncTaskExecutor.execute(new Processor(count));
        }
    }


    static class Processor implements Runnable{
        private final AtomicLong count;
        Processor(AtomicLong count) {
            this.count = count;
        }
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            log.info("线程：{}，起始值:{}", name, count.get());
            int i = 0;
            while (++i < 10) {
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(count.addAndGet(2));
            }
            System.out.println("最后的数量："+count.get());
        }
    }

}
