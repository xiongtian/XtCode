package com.study.day.test201001;

public class test03 {

    public static void main(String[] args) throws InterruptedException {

        Clerk3 clerk3 = new Clerk3();
        Producer3 producer3 = new Producer3(clerk3);
        Consumer3 consumer3 =new Consumer3(clerk3);


        new Thread(producer3,"生产者").start();
        new Thread(consumer3,"消费者").start();

    }
}

class Clerk3{

    private int product;

    public synchronized void get(){

        while (product >= 2) {
            System.out.println("商品已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+ ++ product);
        this.notifyAll();
    }

    public synchronized  void sale(){
        while (product <= 0) {
            System.out.println("商品缺货了！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + --product);
        this.notify();
    }
}
class Producer3 implements Runnable{
    private Clerk3 clerk3;

    public Producer3(Clerk3 clerk3) {
        this.clerk3 = clerk3;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk3.get();
        }
    }

}
class Consumer3 implements Runnable{
    private Clerk3 clerk3;

    public Consumer3(Clerk3 clerk3) {
        this.clerk3 = clerk3;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk3.sale();
        }
    }
}