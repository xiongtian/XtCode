package com.study.day.test200930;

import java.util.Arrays;

/**
 * @author xiongtian
 * @create 2020/10/1-10:07
 */
public class test03 {
    public static void main(String[] args) {
        int[] values={3,1,6,2,9,0,7,4,5,8};
        bubbleSort(values);
        System.out.println(Arrays.toString(values));
    }
    public static void bubbleSort(int[] arrs){
        int temp;
        for (int  i=0;i<arrs.length-1;i++) {
            boolean flag = true;
            for (int j=0;j<arrs.length-1-i;j++) {
                if (arrs[j]>arrs[j+1]) {
                    temp = arrs[j];
                    arrs[j] = arrs[j+1];
                    arrs[j+1] = temp;

                    flag = false;
                }
            }

            if (false) {
                break;
            }
        }
    }
}
