package com.example.justlhyutils.hashCode;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum FactorEnum {

        c_time_now("c_time_now", "系统计算时间",0),
        c_time_latest_delivery("c_time_latest_delivery", "用户最晚送达时间",0),
        c_time_earliest_delivery("c_time_earliest_delivery", "用户最早送达时间",0),
        c_time_payed("c_time_payed", "支付的时间", 0),
        v_time_warehouse_released("v_time_warehouse_released", "仓作业实际释放的时间",0),
        v_time_warehouse_finished("v_time_warehouse_finished", "仓作业实际完成的时间", 0),
        v_time_delivery_released("v_time_delivery_released", "配送实际释放的时间", 0),
        c_time_warehouse_latest_finish("c_time_warehouse_latest_finish", "仓内截止最晚时间(订单给的，类似于京东会有)", 0),
        c_all_time_difference("c_all_time_difference", "从支付时间到最晚送达时间的时间差", 1),
        dv_released("dv_released", "配送提前释放时长", 1),
        wh_released("wh_released", "仓内拣货提前释放时长", 1),
        ff_community("ff_community", "小区履约时长", 1),
        ff_fence("ff_fence", "分层围栏履约时长", 1),
        ff_basic("ff_basic", "平台履约时长", 1),
        ff_default("ff_default", "默认履约时长", 1),
        ff_strategy("ff_strategy", "策略履约加时", 1),
        ff_emergency("ff_emergency", "紧急履约加时", 1),
        wh_community("wh_community", "小区仓内时长", 1),
        wh_fence("wh_fence", "分层围栏仓内时长", 1),
        wh_basic("wh_basic", "平台仓内时长", 1),
        wh_default("wh_default", "默认仓内时长", 1),
        wh_strategy("wh_strategy", "策略仓内加时", 1),
        wh_emergency("wh_emergency", "紧急仓内加时", 1),
        dv_community("dv_community", "小区配送时长", 1),
        dv_fence("dv_fence", "分层围栏配送时长", 1),
        dv_basic("dv_basic", "平台配送时长", 1),
        dv_default("dv_default", "默认配送时长", 1),
        dv_strategy("dv_strategy", "策略配送加时", 1),
        dv_emergency("dv_emergency", "紧急配送加时", 1),
        wh_algorithm("wh_algorithm", "算法侧计算的仓内时长", 1),
        dv_algorithm("dv_algorithm", "算法侧计算的配送时长", 1),
        ;
        private final String value;
        private final String description;

        private final int type;

        FactorEnum(String value, String description,int type) {
            this.value = value;
            this.description = description;
            this.type = type;
        }
        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public int getType() {
            return type;
        }

        public static String parseDate(String a){
            if("null".equals(a)){
                return a;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
            return sdf.format(new Date(Long.parseLong(a)));
        }

        public static String parseMillion(String a) {
            if("null".equals(a)){
                return a;
            }
            long l = Long.parseLong(a);
            return String.valueOf(l / 1000 / 60)+"分钟";
        }

        public static String getChineseDescription(String value) {
            FactorEnum[] values = values();
            for (FactorEnum factorEnum : values) {
                if (factorEnum.getValue().equals(value)) {
                    return factorEnum.getDescription();
                }
            }
            return value;
        }

    }
