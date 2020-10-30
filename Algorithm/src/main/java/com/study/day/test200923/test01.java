package com.study.day.test200923;

import java.util.Arrays;

/**
 * @author xiongtian
 * @create 2020/9/23-22:28
 */
public class test01 {
    public static void main(String[] args) {
        int[] arrs = {8, 9, 7, 5, 2, 3, 0, 1, 4, 5};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }

    public static void quickSort(int [] arrs,int left,int right){

        // 结束递归的条件
        if (left>right){
            return;
        }

        int pivot = arrs[left];
        int i =left;
        int j=right;


        while (i<j) {
            while(arrs[j]>=pivot && i<j) {
                j--;
            }
            while(arrs[i]<=pivot && i<j) {
                i++;
            }
            if (i<j) {
                // 将满足条件的进行位置的互换
                int tempt= arrs[i];
                arrs[i] =arrs[j];
                arrs[j]= tempt;
            }
        }

        // 交换
        arrs[left] =arrs[i];
        arrs[i] = pivot;  // 将基准放到合适的位置

        // 递归进行遍历
        quickSort(arrs,left,i-1);
        quickSort(arrs,i+1,right);


    }
}
