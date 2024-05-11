package com.example.justlhyutils.thread;/**
 * Created by dengbo on 2018/3/22.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 多线程执行抽象类
 *
 * @author dengbo
 * @create 2018-03-22 11:11
 **/
@Slf4j
public abstract class AbstractThreadListExecute<R, T> {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private List<T> list;

    private String message;

    //批处理列表一次默认10条数据
    private int batchNum = 10;

    public AbstractThreadListExecute(ThreadPoolTaskExecutor threadPoolTaskExecutor, List<T> list, String message) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.list = list;
        this.message = message;
    }

    public AbstractThreadListExecute(ThreadPoolTaskExecutor threadPoolTaskExecutor, List<T> list, String message, int batchNum) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.list = list;
        this.message = message;
        this.batchNum = batchNum;
    }

    /**
     * 此方法 子线程抛异常后，会中断执行，返回已执行成功子线程的执行结果
     * @return
     */
    public List<R> execute() {
        Long startTime = System.currentTimeMillis();
        log.info("{}执行开始:{}", message, startTime);
        List<Future> futures = new ArrayList<>();
        //获取单次处理的数量
        int singleNum = getSingleNum(list.size());
        int threadNum = 0;
        for (int i = 0, size = list.size(); i < size; i += singleNum) {
            threadNum++;
            log.info("{} 启动线程:{}", message, threadNum);
            if (list.size() >= singleNum) {
                final List<T> executeList = list.subList(0, singleNum);
                Future future = threadPoolTaskExecutor.getThreadPoolExecutor().submit(CallableWrapper.of(() -> run(executeList)));
                futures.add(future);
                list = list.subList(singleNum, list.size());
                continue;
            }
            Future future = threadPoolTaskExecutor.getThreadPoolExecutor().submit(CallableWrapper.of(() -> run(list)));
            futures.add(future);
        }
        List<R> result = getResult(futures);
        Long endTime = System.currentTimeMillis();
        log.info("{}执行结束，开始时间:{}，结束时间:{},相差时间:{}", message, startTime, endTime, endTime - startTime);
        return result;
    }


    /**
     * 此方法 子线程抛异常后，会等待其他子线程执行，返回所有执行成功子线程的执行结果
     * @return
     */
    public void executeReturn(List<R> result) {
        Long startTime = System.currentTimeMillis();
        log.info("{} executeReturn 执行开始:{}", message, startTime);
        List<Future> futures = new ArrayList<>();
        //获取单次处理的数量
        int singleNum = getSingleNum(list.size());
        int threadNum = 0;
        for (int i = 0, size = list.size(); i < size; i += singleNum) {
            threadNum++;
            log.info("{} executeReturn 启动线程:{}", message, threadNum);
            if (list.size() >= singleNum) {
                final List<T> executeList = list.subList(0, singleNum);
                Future future = threadPoolTaskExecutor.getThreadPoolExecutor().submit(CallableWrapper.of(() -> run(executeList)));
                futures.add(future);
                list = list.subList(singleNum, list.size());
                continue;
            }
            Future future = threadPoolTaskExecutor.getThreadPoolExecutor().submit(CallableWrapper.of(() -> run(list)));
            futures.add(future);
        }
        getResult(futures,result);
        Long endTime = System.currentTimeMillis();
        log.info("{} executeReturn 执行结束，开始时间:{}，结束时间:{},相差时间:{}", message, startTime, endTime, endTime - startTime);
    }

    //根据总数分配单次处理的量
    private int getSingleNum(int count) {
        int singleNum = batchNum;

        if (count > 150) {
            singleNum = 15;
        }
        if (count > 300) {
            singleNum = 25;
        }
        if (count > 500) {
            singleNum = 40;
        }
        if (count > 800) {
            singleNum = 50;
        }
        if (count > 1000) {
            singleNum = 75;
        }
        if (count > 1500) {
            singleNum = 100;
        }
        if (count > 2000) {
            singleNum = 150;
        }
        if (count > 3000) {
            singleNum = 250;
        }
        if (count > 5000) {
            singleNum = 300;
        }
        if (count > 7500) {
            singleNum = 400;
        }
        if (count > 10000) {
            singleNum = 500;
        }
        return singleNum;
    }


    //此方法 子线程抛异常后，会中断执行，返回已执行成功子线程的执行结果
    private List<R> getResult(List<Future> futures) {
        //获取线程中的异常
        try {
            List<R> ts = new ArrayList();
            for (Future future : futures) {
                Object result = future.get();//等待阻塞
                if (result instanceof List) {
                    ts.addAll((List<R>) result);
                } else {
                    ts.add((R) result);
                }
            }
            return ts;
        } catch (Exception e) {
            log.error("执行错误" + message, e);
            throw new RuntimeException(getRealMessage(e));
        }
    }

    //此方法 子线程抛异常后，会等待其他子线程执行，返回所有执行成功子线程的执行结果
    private void getResult(List<Future> futures,List<R> ts) {
        Exception throwException=null;
            for (Future future : futures) {
                try{
                    Object result = future.get();//等待阻塞
                    if (result instanceof List) {
                        ts.addAll((List<R>) result);
                    } else {
                        ts.add((R) result);
                    }
                }catch (Exception e){
                    log.error("执行错误 getResult" + message, throwException);
                    throwException = e;
                }
            }

        if (throwException != null) {
            log.error("执行错误" + message, throwException);
            throw new RuntimeException(getRealMessage(throwException));
        }
    }

    // 获取嵌套异常中的最终信息
    public static String getRealMessage(Throwable e) {
        // 如果e不为空，则去掉外层的异常包装
        while (e != null) {
            Throwable cause = e.getCause();
            if (cause == null) {
                return e.getMessage();
            }
            e = cause;
        }
        return "";
    }

    protected abstract Object run(List<T> ts);

}
