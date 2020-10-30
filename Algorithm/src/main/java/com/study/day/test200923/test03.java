package com.study.day.test200923;



import java.util.Arrays;


/*
* 为什么要从右边开始呢?
* 因为要保证在停止时左边的数必须必基准数小，右边的必基准数大
*
* */

public class test03 {
    public static void main(String[] args) {
        int[] arrs = {6,1,2,7,9};
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));
    }

    public static void quickSort(int[] arrs,int left,int rigth){
        while (left>rigth) {
            return;
        }

        int pivot = arrs[left];
        int i = left;
        int j =rigth;

        while (i<j) {
            while (pivot<= arrs[j] && i<j) {
                j--;
            }
            while ((pivot>=arrs[i] && i<j)) {
                i++;
            }

            if (i<j) {
                int tempt = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] = tempt;
            }
        }


        arrs[left] =arrs[i];
        arrs[i] = pivot;
        quickSort(arrs, left, i - 1);
        quickSort(arrs,i+1,rigth);

    }


}
