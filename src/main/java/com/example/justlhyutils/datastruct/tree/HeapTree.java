package com.example.justlhyutils.datastruct.tree;

import com.alibaba.fastjson.JSON;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/3/20 09:37
 */
public class HeapTree {

    public static void main(String[] args) {
        //用数组的形式来表示最大堆。//[15,12,8,7,5,2,3,4,2]
        int[] arr = {15, 2, 8, 12, 5, 2, 3, 4, 7};
        int size = arr.length / 2;
        for (int i = 0; i < size; i++) {
            adjustArr2Heap(arr, i, arr.length);
        }
        System.out.println(JSON.toJSONString(arr));
        //数组如何快速删除第一个元素

    }

    private static void adjustArr2Heap(int[] array,int index,int heapSize) {
        int iMax = index;
        int iLeft = 2 * index + 1;
        int iRight = 2 * (index + 1);
        if (iLeft < heapSize && array[index] < array[iLeft]) {
            iMax = iLeft;
        }
        if (iRight < heapSize && array[iMax] < array[iRight]) {
            iMax = iRight;
        }
        if (iMax != index) {
            swap(array, iMax, index);
            adjustArr2Heap(array, iMax, heapSize); // 递归调整
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
