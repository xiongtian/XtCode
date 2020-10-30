package com.xiongtian.interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Volatile 不保证原子性
 *   不可分割，原子性，完整性，
 *   也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 * 如何解决？
 *      1、使用synchronized
 *      2、使用AtomicInteger
 * */
public class VolatileNoAtomic {

    public static void main(String[] args) {

        MyData02 myData02 = new MyData02();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {

                for (int j = 1; j <=1000; j++) {
                    myData02.addPlus();
                    myData02.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面的运行结果
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int finally value: "+myData02.num);
        System.out.println(Thread.currentThread().getName()+"\t atomic finally value: "+myData02.atomicInteger);

        /*结果： main	 finally value: 19505
        * */
    }
}

class MyData02 {


    volatile int num = 0;

    // 原子类的整型
    AtomicInteger atomicInteger = new AtomicInteger(); // 默认值是0

    // 注意： number前面是加了volatile的
    public  void addPlus() {
        num++;
    }

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }

}