package com.study.day.test201001;

/**
 * @author xiongtian
 * @version 1.0
 * @date 2020/11/2 18:00
 */
public class test06 {

    public static void main(String[] args) {
        Clerk06 clerk =new Clerk06();
        Consumer6 consumer =new Consumer6(clerk);
        Producer6 producer =new Producer6(clerk);
        new Thread(consumer,"消费者").start();
        new Thread(producer,"生产者").start();

    }
}

class Clerk06 {
    private int product = 0;


    public synchronized void get() {
        while (product >= 2) {
            System.out.println("商品已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();
    }

    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("商品已售馨！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ --product);
        this.notifyAll();
    }
}
class Consumer6 implements Runnable{

    private Clerk06 clerk;

    public Consumer6(Clerk06 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.sale();
        }
    }
}
class Producer6 implements Runnable{

    private Clerk06 clerk;

    public Producer6(Clerk06 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.get();
        }
    }
}
