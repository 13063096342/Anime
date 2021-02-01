package com.java.sdk.algorithm;

import java.util.Arrays;

/**
 * @author chenfh
 * @date 2020-10-20 10:59
 */
public class MyHeapSort {
    public static void main(String[] args) {
        int[] arr = {17, 3, 20, 8, 6, 11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        //初始化堆
        for (int i = (arr.length-1) / 2; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //初始化堆
        System.out.println("初始化arr:"+Arrays.toString(arr));
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆的首尾元素调换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //重新排序  最末元素为最大值。
            adjustHeap(arr, 0, i);
        }
    }

    public static void adjustHeap(int[] arr, int parentNode, int length) {
        //父节点
        int parent = arr[parentNode];
        //左子结点
        int childNode = 2 * parentNode + 1;

        while (childNode < length) {
            if (childNode + 1 < length && arr[childNode + 1] > arr[childNode]) {
                childNode++;
            }
            if (arr[childNode] <= arr[parentNode]) {
                break;
            }
            //交换父子结点
            arr[parentNode] = arr[childNode];
            arr[childNode] = parent;
            //重排子结点
            parentNode = childNode;
            childNode = childNode * 2 + 1;
        }
    }
}

