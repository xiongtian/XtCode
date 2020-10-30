package com.study.day.test200923;

import java.util.Arrays;

public class test05 {

    public static void quickSort(int[] arrs,int left,int rigth) {

        if (left>rigth) {
            return;
        }
        int pivot = arrs[left];
        int i= left;
        int j= rigth;

        while (i<j) {
            while (arrs[j]>=pivot && i<j) {
                j--;
            }
            while (arrs[i] <= pivot && i<j) {
                i++;
            }

            int tempt = arrs[i];
            arrs[i] = arrs[j];
            arrs[j] = tempt;
        }

        arrs[left] = arrs[i];
        arrs[i] = pivot;
        quickSort(arrs, left, i - 1);
        quickSort(arrs,i+1,rigth
        );
    }
}
