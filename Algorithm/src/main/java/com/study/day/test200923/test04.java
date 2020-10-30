package com.study.day.test200923;

import java.util.Arrays;

public class test04 {
    public static void main(String[] args) {
        int[] arrs = {6,1,2,7,9};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }
    public static void quickSort(int[] arrs, int left, int right) {
        if (left > right) {
            return;
        }

        int pivot = arrs[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (pivot <= arrs[j] && i < j) {
                j--;
            }
            while (pivot >= arrs[i] && i < j) {
                i++;
            }


            int tempt = arrs[i];
            arrs[i] = arrs[j];
            arrs[j] = tempt;
        }

        arrs[left] = arrs[i];
        arrs[i] = pivot;

        quickSort(arrs,left,i-1);
        quickSort(arrs,i+1,right);

    }
}
