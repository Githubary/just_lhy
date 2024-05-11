package com.example.justlhyutils.hashCode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.A;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/12/13 17:27
 */
public class testMycat {

    private final static Pair<String, String> SCENARIO_FULFIL_TASK = Pair.of("fulfil-task", "2");
    private final static LocalDate START_DATE = LocalDate.of(2020, 01, 01);

    private final static String SHOP_ID = "90B0,90C3,90C7,90D9,90I4,90I5,90I9,90K4,90P3,90V5,9113," +
            "9194,9309,9377,9522,9527,9542,9546,9589,9591,9720,9721,9729,9221,9417,9436,9479,9485,9498,95DX,95E5," +
            "95E9,95EG,95EH,95EL,95EP,95F1,95FS,95FW,95G8,95GG,95GK,9630,9631,9639,9650,9654,9697,9833,9837,9895," +
            "90E1,90F7,90G4,90M2,90M6,9131,9148,9150,9327,9346,9391,9502,9515,9519,9560,9564,9747,9762,9MG4,9229," +
            "9231,9293,9297,9408,9410,9427,9472,9476,9489,9495,95E2,95EA,95EE,95ET,95EX,95F5,95FH,95FZ,9641,9645," +
            "9820,9824,9843,9M4M,9030,9213,9225,9232,9290,9294,9423,9428,9473,9477,9492,95DJ,95DN,95EB,95EF,95EU," +
            "95EY,95F6,95FI,95FM,95GA,9642,9646,9658,9659,9821,9825,9844,9093,9097,90A8,90H2,90L5,90N8,90R7,9137," +
            "9143,9156,9335,9341,9358,9508,9553,9557,9576,9749,9768,9770,9774,90C6,90I8,90K2,90K7,90P2,90P6,90V4," +
            "9112,9174,9178,9193,9197,9308,9310,9314,9376,9388,9389,9526,9540,9541,9545,9594,9724,9728,90C1,90I7," +
            "90K6,90P1,90P5,90V7,9108,9109,9111,9173,9192,9196,9306,9313,9371,9375,9525,9529,9544,9548,9586,9710," +
            "9723,9727,9739,9785,9791,9082,9086,90E2,90E6,90G5,90L0,90M3,90N9,90S4,90S5,90S9,90T1,9132,9145,9151," +
            "9328,9330,9343,9392,9396,9503,9516,9561,9565,9578,9740,9744,9087,90A0,90E3,90G2,90M4,90M8,90R3,90S6," +
            "90T2,9146,9331,9344,9348,9500,9517,9566,9579,9741,9757,9758,9760,9MFM,9MG6,3T01,9048,9214,9227,9291,9295,9406,9412,9425,9431,9470,9474,9493,95DO,95E0,95EC,95EQ,95ER,95EV,95EZ,95F3,95FJ,95GB,9643,9647,9822,9826,9838,9841,9845,9217,9242,9281,9285,9415,9419,9421,9434,9483,95DV,95DZ,95E7,95EJ,95EN,95FB,95FP,95FQ,95FY,95G1,95G2,9633,9637,9656,9694,9695,9699,9847,9850,9854,9892,9897,9M4V,9092,90A7,90F6,90G9,90H1,90L4,90L8,90N3,90R5,90R6,90U1,9136,9142,9155,9161,9333,9334,9338,9340,9353,9357,9507,9552,9556,9569,9575,9750,9754,9766,9773,9098,90A5,90A9,90F0,90F4,90G6,90G7,90H3,90L1,90R8,90S0,90T7,9138,9336,9355,9359,9397,9398,9504,9509,9550,9554,9558,9702,9756,9769,9771,9MFK,9060,9073,9256,9271,9275,9279,9454,9458,95C4,95D7,95IE,9603,9604,9608,9610,9672,9689,9691,9801,9802,9806,9870,9887,9M7J,9M7N,90C4,90C8,90J2,90K9,90P0,90P4,90Q6,90Q7,90V1,9114,9126,9127,9171,9172,9176,9191,9312,9370,9374,9378,9524,9530,9543,9547,9592,9722,9726,9788,9790,9061,9074,9080,9207,9208,9252,9253,9272,9276,9450,9451,9455,95B9,95CD,95D8,95GY,95GZ,95HR,95HV,95I3,95I7,9605,9609,9611,9624,9628,9692,9807,9883,9M6D,9090,90A6,90E9,90F5,90G8,90H0,90L3,90L7,90R9,90T3,90T4,90T8,90U0,9141,9153,9160,9351,9352,9356,9399,9506,9512,9551,9555,9570,9574,9708,9753,9772,9MG7,90B3,90B7,90D6,90J3,90J4,90J8,90K0,90Q2,9103,9122,9169,9184,9188,9301,9305,9367,9379,9382,9532,9536,9581,9585,9598,9715,9730,9734,9777,9783,9796,3T02,9211,9230,9289,9407,9413,9426,9471,9475,9488,9494,95A9,95DL,95DP,95E1,95ED,95ES,95EW,95F4,95FG,95FK,95FO,95G0,95GC,9640,9648,9823,9827,9842,9891,9M98,9222,9234,9239,9241,9280,9284,9288,9420,9432,9433,9437,9482,9486,9499,95DY,95EI,95EM,95FA,95FE,95FT,95G5,95G9,95GH,95GL,9632,9636,9651,9655,9834,9M4U,9218,9220,9224,9237,9282,9286,9298,9299,9416,9422,9435,9480,9484,9497,95DR,95DS,95DW,95E4,95E8,95EK,95EO,95F0,95FC,95FV,95G3,95G7,95GE,95GF,95GJ,9634,9638,9653,9657,9696,9829,9832,9849,9851,90B4,90B8,90D7,90I2,90J5,90K1,90Q3,90U6,90W0,90W1,9100,9123,9166,9180,9181,9185,9189,9302,9319,9364,9368,9383,9520,9533,9537,9582,9599,9712,9716,9778,9780,9797,9084,9088,90A1,90E0,90E4,90G3,90M0,90M5,90M9,90R4,9130,9147,9326,9332,9345,9349,9390,9394,9501,9513,9514,9518,9563,9567,9746,9759,9761,9MGJ,90B1,90B2,90B6,90D5,90H8,90J7,90P9,90Q1,90Q5,9106,9119,9121,9164,9168,9170,9183,9300,9304,9317,9323,9362,9381,9385,9535,9539,9580,9584,9714,9718,9733,9737,9775,9776,9795,9799,9071,9201,9243,9403,9442,9446,9461,95B2,95C9,95D1,95GS,95H4,95H8,95HG,95HK,95HZ,9602,9615,9619,9664,9676,9677,9800,9817,9M7S,9M7W,9M8F,9M8G,9M8K,9014,9057,9076,9278,9453,9457,9469,95C3,95CB,95CF,95D6,95HP,95I1,95I5,95IC,95ID,9622,9626,9669,9675,9690,9805,9809,9867,9873,9M7I,9M7M,9M8A,9NT9,9063,9204,9247,9266,9445,9449,9460,9464,9468,95DC,95GR,95GV,95H3,95HJ,95I9,9601,9614,9618,9812,9816,9859,9861,9M5W,9M7Q,9M7R,9M7V,9M8J,9PU8,90B5,90B9,90D0,90D8,90H7,90I3,90P7,90U2,90U3,90U7,90W2,9101,9117,9118,9124,9162,9167,9182,9315,9316,9360,9361,9365,9369,9380,9384,9521,9534,9538,9583,9595,9596,9713,9717,9732,9736,9779,9781,9798,9050,9067,9203,9246,9265,9269,9444,9448,9463,9467,95B0,95C7,95CI,95D3,95DB,95GU,95H2,95H6,95HE,95HI,95IA,9600,9612,9662,9666,9681,9810,9815,9860,9864,9877,9M5Z,9M6N,9M7F,9M7U,9M7Y,9M8I,9062,9209,9254,9258,9260,9273,9277,9452,95C2,95CA,95CE,95D4,95D9,95HS,95HW,95I0,95I4,95I8,9606,9625,9668,9670,9674,9804,9865,9866,9M5M,9M6A,9M7G,9M7H,9M7L,9M7P,9M8D,9NU0,9053,9202,9206,9245,9251,9264,9268,9404,9443,9447,9462,95B3,95C5,95C6,95GO,95GP,95GT,95GX,95H1,95H5,95H9,95HH,9665,9678,9680,9684,9814,9818,9856,9857,9863,9M0K,9M5Y,9M6H,9M6I,9M7E,9M7T,9M7X,9M8H,9M8L";

    public static void main(String[] args) {
        String expression = "return v_time_delivery_released + (c_all_time_difference == null ? (nvl(dv_community,dv_fence,dv_basic,dv_default)+zero(dv_strategy)+zero(dv_emergency)) : (c_all_time_difference - (nvl(wh_community,wh_fence,wh_basic,wh_default)+zero(wh_strategy)+zero(wh_emergency))))";
        String expressionValue = "return 1702518657350 + (5400000 == null ? (nvl(null,2700000,2700000,2700000)+zero(0)+zero(1800000)) : (5400000 - (nvl(null,900000,900000,900000)+zero(0)+zero(0))))";
        FactorEnum[] factors = FactorEnum.values();
        String[] split = org.springframework.util.StringUtils.tokenizeToStringArray(expression, "+-()?=,: ");
        String[] split2 = org.springframework.util.StringUtils.tokenizeToStringArray(expressionValue, "+-()?=,: ");
        Map<String,String> map =  new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            map.put(split[i],split2[i]);
        }
        for (FactorEnum factor : factors) {
            if (expression.contains(factor.getValue())) {
                expression = expression.replace(factor.getValue(), factor.getDescription());
                int type1 = factor.getType();
                if (type1 == 0) {
                    expressionValue = expressionValue.replace(map.get(factor.getValue()), FactorEnum.parseDate(map.get(factor.getValue())));
                }
                if (type1 == 1) {
                    expressionValue = expressionValue.replace(map.get(factor.getValue()), FactorEnum.parseMillion(map.get(factor.getValue())));
                }
            }
        }
        System.out.println(expressionValue);
        System.out.println(coinChange(new int[]{186,419,83,408},6249));
        System.out.println(wordBreak("aebbbbs", Lists.newArrayList("a","aeb","ebbbb","s","eb")));
        System.out.println("asdsa".substring(2,4));
//        System.out.println( 8 & 8);
//        System.out.println( 4 & 8);
//        System.out.println( 16 & 8);
//        Map<Long, List<String>> map = new HashMap<>();
//        String[] split = SHOP_ID.split(",");
//        for (String shopId : split) {
//            Long mycatId = getMycatId(shopId);
//            long l = mycatId % 32;
//            List<String> strings = map.get(l);
//            if (strings == null) {
//                List<String> list = new ArrayList<>();
//                list.add(shopId);
//                map.put(l, list);
//            } else {
//                strings.add(shopId);
//            }
//        }
//        Set<Long> longs = map.keySet();
//        for (Long aLong : longs) {
//            List<String> strings = map.get(aLong);
//            System.out.println(aLong+":"+strings.size());
//        }
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
    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     */

    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    private static Long getMycatId(String warehouseId){
        double v = Math.pow(10, 7) - 1;
        int hashSize = 4;
        int dateSize = 4;
        int sequenceSize = 7;
        int _number = 10;
        int shortHash = Math.abs(warehouseId.hashCode()) % 10000;
        // 保证末尾不为0，从而保证整个id的第二位不为0
        if (shortHash % _number == 0) {
            shortHash++;
        }
        String reversedHash = StringUtils.reverse(StringUtils.leftPad(String.valueOf(shortHash), hashSize, '0'));
        String sequence = makeSequence(SCENARIO_FULFIL_TASK.getLeft() + reversedHash, sequenceSize);
        String str = MessageFormat.format("{0}{1}{2}{3}",
                SCENARIO_FULFIL_TASK.getRight(),
                reversedHash,
                calculateDateDifference(dateSize),
                sequence);
        return Long.parseLong(str);
    }

    private static String makeSequence(String businessCode, int size) {
        System.out.println(businessCode);
        // try to invoke id service to apply one number
        // make one locally if exception occurs
        try {
            long number = 1;
            int _limit = (int) Math.pow(10, size) - 1;
            if (number > _limit) {
                throw new RuntimeException("最大支持" + size + "位");
            }
            return StringUtils.reverse(StringUtils.leftPad(String.valueOf(number), size, '0'));
        } catch (Exception e) {
            return "";
        }
    }

    private static String calculateDateDifference(int len) {
        LocalDate today = LocalDate.now();
        long daysDiff = ChronoUnit.DAYS.between(START_DATE, today);
        return StringUtils.leftPad(String.valueOf(daysDiff), len, '0');
    }
}
