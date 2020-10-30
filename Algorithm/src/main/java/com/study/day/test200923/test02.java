package com.study.day.test200923;

import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {
        int[] arrs = {8, 9, 7, 5, 2, 3, 0, 1, 4, 5};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }

    public static void quickSort(int[] arrs,int left,int rigth) {
        if (left>rigth) {
            return;
        }
        int pivot = arrs[left];
        int i = left;
        int j = rigth;
        while (i<j) {


            while (arrs[j]>=pivot && i<j) {
                j--;
            }
            while (arrs[i]<=pivot && i<j) {
                i++;
            }
            if (i<j) {
                int tempt = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] = tempt;
            }

        }

        arrs[left] = arrs[i];
        arrs[i] = pivot;


        quickSort(arrs,left,i-1);
        quickSort(arrs,i+1,rigth);
    }
}
