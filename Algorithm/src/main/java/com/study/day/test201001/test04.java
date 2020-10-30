package com.study.day.test201001;

/**
 * @author xiongtian
 * @version 1.0
 * @date 2020/10/29 10:17
 */
public class test04 {
    public static void main(String[] args) {
        Clerk4 clerk = new Clerk4();
        Productor4 productor =new Productor4(clerk);
        Consumer4 consumer = new Consumer4(clerk);

        new Thread(productor,"生产者").start();
        new Thread(consumer,"消费者").start();
    }
}
class Clerk4{

    private int product;


    public synchronized void get(){
        while (product >= 2) {
            System.out.println("商品已满!");
            try { this.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();
    }


    public synchronized  void sale(){
        while (product<=0){
            System.out.println("商品已售光！");
            try { this.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+ ":"+ --product);
        this.notifyAll();
    }
}

/**
 * 生产者
 */
class Productor4 implements Runnable {

    private Clerk4 clerk;

    public Productor4(Clerk4 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.get();
        }
    }
}
/*
* 消费者
* */
class Consumer4 implements Runnable
{

    private Clerk4 clerk;

    public Consumer4(Clerk4 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.sale();
        }
    }
}