package com.study.day.test200930;

import java.util.Arrays;
/*
* 冒泡排序*/
/**
 * @author xiongtian
 * @create 2020/9/30-1:44
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] values={3,1,6,2,9,0,7,4,5,8};
        bubbleSort(values);
        System.out.println(Arrays.toString(values));
    }

    public static void bubbleSort(int[] arrs) {
        // 中间的临时变量
        int temp = 0;

        for (int i = 0; i < arrs.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arrs.length - 1 - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    temp = arrs[j+1];
                    arrs[j+1] = arrs[j];
                    arrs[j] = temp;

                    flag=false;
                }
            }
            if (false){
                break;
            }
        }
    }
}
