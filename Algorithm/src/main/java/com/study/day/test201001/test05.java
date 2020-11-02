package com.study.day.test201001;

/**
 * @author xiongtian
 * @version 1.0
 * @date 2020/11/2 10:10
 */
public class test05 {
    public static void main(String[] args) {

        Clerk5 clerk = new Clerk5();
        Consumer5  consumer = new Consumer5(clerk);
        Producer5 producer = new Producer5(clerk);
        new Thread(consumer,"消费者").start();
        new Thread(producer,"生产者").start();
    }
}

class Clerk5 {
    private int product = 0;

    public synchronized void get() {
        while (product >= 2) {
            System.out.println("商品已满!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();
    }

    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("商品不足！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();
    }
}

class Consumer5 implements Runnable {
    private Clerk5 clerk;

    public Consumer5(Clerk5 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}

class Producer5 implements Runnable {
    private Clerk5 clerk;

    public Producer5(Clerk5 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}