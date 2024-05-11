package com.example.justlhyutils.datastruct.arr;

import com.alibaba.fastjson.JSON;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/3/19 14:44
 */
public class FindSameSample {


    //从两个有序数组中找到相同元素，并统计其个数
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 4, 5, 7, 10, 11, 23};
        int[] b = {2, 3, 4, 5, 5, 6, 8};
        int[] c = findSame(a, b);
        System.out.println(JSON.toJSONString(c));
    }

    private static int[] findSame(int[] a, int[] b) {
        int clength = Math.max(a.length,b.length);
        int[] c = new int[clength];
        int i=0,j=0,k=0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                c[k++] = a[i];
                i++;
                j++;
            }
        }
        return c;
    }

}
