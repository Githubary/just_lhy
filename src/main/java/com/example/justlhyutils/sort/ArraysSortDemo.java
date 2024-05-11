package com.example.justlhyutils.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/3/19 10:59
 */
public class ArraysSortDemo {

    public static void main(String[] args) {
        System.out.println(Byte.MIN_VALUE);
        String a = "32321";
        String b = "32132";
        System.out.println(a.compareTo(b));
        String[] arr = new String[]{"3","32","321","22"};
        doCompare(arr);
    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     */

    private static void doCompare(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a = o1+o2;
                String b = o2+o1;
                return a.compareTo(b);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        System.out.println(sb);
    }


}
