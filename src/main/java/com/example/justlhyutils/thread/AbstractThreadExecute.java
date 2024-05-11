package com.example.justlhyutils.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 多线程执行抽象类
 *
 * @author dengbo
 * @create 2018-03-22 11:11
 **/
@Slf4j
public abstract class AbstractThreadExecute<R,T> {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private List<T> list;

    /**
     * 任务执行描述
     */
    private String message;

    //批处理列表一次默认50条数据
    private int batchNum = 100;


    public AbstractThreadExecute(ThreadPoolTaskExecutor threadPoolTaskExecutor, List<T> list, String message) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.list = list;
        this.message = message;
    }

    public AbstractThreadExecute(ThreadPoolTaskExecutor threadPoolTaskExecutor, List<T> list, String message, int batchNum) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.list = list;
        this.message = message;
        this.batchNum = batchNum;
    }

    public void executeSingle(){
        Long startTime = System.currentTimeMillis();
        log.info("{}执行开始:{}",message,startTime);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            final T t = list.get(i);
            threadPoolTaskExecutor.getThreadPoolExecutor().submit(CallableWrapper.of(() -> run(t)));
            log.info("{} 启动线程:{}", message, i + 1);
        }
        Long endTime = System.currentTimeMillis();
        log.info("{}执行结束，开始时间:{}，结束时间:{},相差时间:{}",message,startTime,endTime,endTime-startTime);
    }


    @NotNull
    private List<R> getResult(List<Future> futures) {
        //获取线程中的异常
        try {
            List<R> ts = new ArrayList();
            for(Future future : futures){
                Object result = future.get();//等待阻塞
                if(result instanceof List){
                    ts.addAll((List<R>) result);
                }else{
                    ts.add((R)result);
                }
            }
            return ts;
        } catch (Exception e) {
            log.error("执行错误"+message,e);
            throw new RuntimeException(getRealMessage(e));
        }
    }


    protected abstract Object run(T t);


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

}
