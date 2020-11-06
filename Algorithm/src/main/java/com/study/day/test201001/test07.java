package com.study.day.test201001;

/**
 * @author xiongtian
 * @version 1.0
 * @date 2020/11/6 10:21
 */
public class test07 {
    public static void main(String[] args) {
        Clerk7 clerk =new Clerk7();

        Productor7 productor = new Productor7(clerk);
        Consumer7 consumer =new Consumer7(clerk);
        new Thread(productor,"生产者1").start();
        new Thread(consumer,"消费者1").start();

        new Thread(productor,"生产者2").start();
        new Thread(consumer,"消费者2").start();
    }
}

class Clerk7{
    int product = 0;

    public synchronized  void get(){
        while(product >= 2){
            System.out.println("商品已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"  :" + ++ product);
        this.notifyAll();
    }


    public synchronized  void sale(){
        while (product <= 0) {
            System.out.println("商品缺货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+ "  :"+ --product);
        this.notifyAll();
    }
}

class Productor7 implements Runnable{
    Clerk7 clerk;

    public Productor7(Clerk7 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.get();
        }
    }
}
class Consumer7 implements Runnable{
    Clerk7 clerk;

    public Consumer7(Clerk7 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.sale();
        }
    }
}