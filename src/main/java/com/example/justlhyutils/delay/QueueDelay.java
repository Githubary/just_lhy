package com.example.justlhyutils.delay;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/2/28 14:12
 */
@Slf4j
public class QueueDelay {

    private final LinkedBlockingQueue<ReleaseTimeUpdateTask> queue = new LinkedBlockingQueue<>(200);
    private ReentrantLock lock = new ReentrantLock();
    private Thread executeThread = null;

    public static void main(String[] args) {
        ReleaseTimeUpdateTask updateTask = new ReleaseTimeUpdateTask(12L);
        Duration duration = Duration.between(updateTask.createTime, LocalDateTime.now());
        System.out.println(duration.toMillis());
        long l = 2 * 1000 - duration.toMillis();
    }

    public void addDelayTask(Long orderId) {
        log.info("addTask task size:" + queue.size());
        if (queue.offer(new ReleaseTimeUpdateTask(orderId))) {
            doTask();
        }
    }

    private void doTask() {
        boolean b = lock.tryLock();
        if (b) {
            //通过executeThread变量防止不停的新建线程
            //本质是单线程sleep的方式进行延迟
            if (!queue.isEmpty() && executeThread == null) {
                executeThread = new Thread(() -> {
                    try {
                        while (true) {
                            ReleaseTimeUpdateTask updateTask = queue.poll(3, TimeUnit.SECONDS);
                            if (updateTask == null) {
                                break;
                            }
                            Duration duration = Duration.between(updateTask.createTime, LocalDateTime.now());
                            long l = 2 * 1000 - duration.toMillis();
                            if (l > 0) {
                                TimeUnit.MILLISECONDS.sleep(l);
                            }
                        }
                    } catch (Exception ignored) {
                    } finally {
                        executeThread = null;
                        lock.unlock();
                    }
                }, "delayThreadName");
                executeThread.start();
            }
        }
    }

    static class ReleaseTimeUpdateTask {
        private Long orderId;
        private LocalDateTime createTime;

        public ReleaseTimeUpdateTask(Long orderId) {
            this.orderId = orderId;
            this.createTime = LocalDateTime.now();
        }
    }
}
