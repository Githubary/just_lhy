//package com.example.justlhyutils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.annotation.JSONField;
//import com.example.justlhyutils.model.Customer;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.yonghui.common.util.MoneyUtils;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.time.DateFormatUtils;
//import org.apache.commons.lang.time.DateUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.DateTime;
//import org.joda.time.LocalTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.temporal.TemporalAccessor;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
//
///**
// * description:
// *
// * @author liuhuayang
// * date: 2024/1/16 11:37
// */
//@Slf4j
//public class test {
//
//    public static final DateTimeFormatter DEFAULT_HOUR_MINUTE_FORMATTER = DateTimeFormat.forPattern("HH:mm");
//    public static void main(String[] args) throws ParseException {
//
//        System.out.println(48&32);
//
//        Calendar instance = Calendar.getInstance();
//        String time = "2023-02-29 00:00:00";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date parse = simpleDateFormat.parse(time);
//        System.out.println(parse);
//        Date date = DateUtils.addYears(parse, 1);
//        System.out.println(JSON.toJSONString(date));
//        BigDecimal goodsPayAmount =BigDecimal.valueOf(52.94);
//        System.out.println(goodsPayAmount);
//        BigDecimal customSalePrice = goodsPayAmount.divide(BigDecimal.valueOf(3), 3, 4);
//        System.out.println(customSalePrice);
//
//        long a = 222;
//        long b = 3;
//        long c = a % b > 0 ? a/b + 1 : a/b;
//        System.out.println(c);
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = createPool(4,4,120,5000);
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
//        threadPoolTaskExecutor.initialize();
//        AtomicLong atomicLong = new AtomicLong();
//        Customer customer = new Customer();
//        customer.setId(atomicLong.getAndAdd(4));
//        System.out.println(JSON.toJSONString(customer));
//        System.out.println(atomicLong.get());
//        AtomicLong count = new AtomicLong();
//        int pageCount = 41 / 4;
//        for (int i = 1; i <= 4; i++) {
//            // 提交任务给线程池处理
//            int startIndex = (i - 1) * pageCount + 1;
//            int endIndex = (i - 1) * pageCount + pageCount;
//            AtomicInteger start = new AtomicInteger(startIndex);
//            ExpectTimeOrderRequest expectTimeOrderRequest = new ExpectTimeOrderRequest();
//            expectTimeOrderRequest.setPage(startIndex);
//            expectTimeOrderRequest.setSize(1);
//            threadPoolTaskExecutor.execute(new PageProcessor(count, 40, start, endIndex,1, new Date(), "operator", String.valueOf(10001), expectTimeOrderRequest));
//        }
//    }
//
//    public static ThreadPoolTaskExecutor createPool(int corePoolSize, int maxPoolSize, int keepAliveSeconds, int queueCapacity) {
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
//        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
//        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
//        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
//        return threadPoolTaskExecutor;
//    }
//
//
//    @Data
//     static  class ExpectTimeOrderRequest implements Serializable {
//
//        private static final long serialVersionUID = 1L;
//        private Integer page = 1;
//
//        private Integer size = 20;
//    }
//
//     static class PageProcessor implements Runnable {
//        private final AtomicLong count;
//        private final long total;
//        private AtomicInteger startIndex;
//        private final int pageSize;
//        private final Date releaseTime;
//        private final String operator;
//        private final String currentUserId;
//        private ExpectTimeOrderRequest requestVO;
//        private int endIndex;
//
//        public PageProcessor(AtomicLong count, long total, AtomicInteger startIndex,int endIndex, int pageSize, Date releaseTime, String operator, String currentUserId, ExpectTimeOrderRequest requestVO) {
//            this.count = count;
//            this.total = total;
//            this.startIndex = startIndex;
//            this.endIndex = endIndex;
//            this.pageSize = pageSize;
//            this.releaseTime = releaseTime;
//            this.operator = operator;
//            this.currentUserId = currentUserId;
//            this.requestVO = requestVO;
//        }
//
//         @Override
//         public void run() {
//             System.out.println(JSON.toJSONString(requestVO));
//             int safeCount = 0;
//             int page = requestVO.getPage();
//             while (safeCount < 100) {
//                 System.out.println("111");
//                 String name = Thread.currentThread().getName();
//                 safeCount++;
//                 requestVO.setPage(page);
//                 requestVO.setSize(pageSize);
//                 try {
//                     search(requestVO);
//                     count.addAndGet(1);
//                     long i = count.get();
//                     int process = (int) ((double) i / total * 100);
//                     if (process == 0) {
//                         process = 1;
//                     }
//                     log.info("当前线程:{},查询的请求:{}，查询时的页数:{}，处理总量:{}，总量:{},进度:{}", name, JSON.toJSONString(requestVO), page,i,total,process);
//                 } catch (InterruptedException e) {
//                     throw new RuntimeException(e);
//                 }
//                 page++;
//                 if (page > endIndex) {
//                    break;
//                 }
//             }
//        }
//    }
//
//    public static void search(ExpectTimeOrderRequest request) throws InterruptedException {
//        String name = Thread.currentThread().getName();
//        Thread.sleep(1000);
//        System.out.println(name + "请求：" + JSON.toJSONString(request));
//    }
//
//    private static LocalTime makeLocalTime(String timeString) {
//        timeString = StringUtils.trim(timeString);
//        //针对订单系统中零点可能出现2400的特殊处理，提前一秒
//        if (StringUtils.equals(timeString, "24:00")) {
//            timeString = "23:59:59";
//        }
//
//        DateTimeFormatter formatter = timeString.length() > 5
//                ? DateTimeFormat.forPattern("HH:mm:ss") : DEFAULT_HOUR_MINUTE_FORMATTER;
//        return LocalTime.parse(timeString, formatter);
//    }
//
//}
