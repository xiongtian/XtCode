package com.study.day.test200923;


import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arrs = {8, 9, 7, 5, 2, 3, 0, 1, 4, 5};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }

    public static void quickSort(int[] arrs, int left, int right) {

        // 递归结束条件
        if (left > right) {
            return;
        }
        // 基准
        int pivot = arrs[left];
        int i = left;
        int j = right;

        while (i < j) {
            // 从右往左寻找比基准小的数
            while (pivot <= arrs[j] && i < j) {
                j--;
            }
            // 从左往右寻找比基准大的数
            while (pivot >= arrs[i] && i<j) {
                i++;
            }
            /*进行交换*/
            if (i < j) {
                int temp = arrs[i];
                arrs[i]  =  arrs[j];
                arrs[j]  =  temp;
            }
        }

        arrs[left] = arrs[i];
        arrs[i] = pivot; // 将基准放到合适的位置
        quickSort(arrs,left,i-1); // 左区域遍历
        quickSort(arrs,i+1,right); // 右区域遍历
    }
}
