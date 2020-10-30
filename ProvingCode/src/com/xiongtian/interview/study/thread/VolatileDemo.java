package com.xiongtian.interview.study.thread;

import java.util.concurrent.TimeUnit;

class MyData{

    //volatile int number = 0; 可见性
    int number = 0;

    public void addTo60(){
        this.number = 60;
    }
}
/*
*
* Volatile 可见性的证明
* */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 让线程暂停
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t come in"+"data:"+myData.number);
        },"AAA").start();


        // main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不再等于0
        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over !");
    }
}


