package com.study.day.test200923;

import java.util.Arrays;

/**
 * @author xiongtian
 * @create 2020/9/30-1:38
 */
public class test08 {

    public static void main(String[] args) {
        int[] arrs = {6,1,2,7,9};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }

    public static void quickSort(int[] arrs,int left,int right){
        while (left > right) {
            return;
        }

        int pivot = arrs[left];
        int i = left;
        int j = right;

        while (i<j) {
            while (arrs[j] >= pivot && i<j) {
                j--;
            }
            while (arrs[i] <= pivot && i<j) {
                i++;
            }

            if (i<j) {
                int temp = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] =temp;
            }
        }

        arrs[left] = arrs[i];
        arrs[i] = pivot;

        quickSort(arrs,left,i-1);
        quickSort(arrs,i+1,right);

    }
}
