package com.study.day.test201001;

public class test02 {



    public static void main(String[] args) {
        Clerk2 clerk2 = new Clerk2();
        Producer2 productor = new Producer2(clerk2);
        Consumer2 consumer = new Consumer2(clerk2);

        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}

class Clerk2{
    private int produce;

    // 进货
    public synchronized void get(){

        while (produce>=1){
            System.out.println("货源充足！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++produce);
        this.notifyAll();
    }

    // 卖货
    public synchronized void sale(){
        while (produce <= 0) {
            System.out.println("缺货中！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName()+":"+ --produce);
        this.notifyAll();
    }
}
class Consumer2 implements Runnable{

    private Clerk2 clerk2;

    public Consumer2(Clerk2 clerk2) {
        this.clerk2 = clerk2;
    }

    @Override
    public void run() {
        for (int i =0;i<10;i++){
            clerk2.sale();

        }
    }
}

class Producer2 implements Runnable {

    private Clerk2 clerk2;


    public Producer2(Clerk2 clerk2) {
        this.clerk2 = clerk2;
    }

    @Override
    public void run() {
        for (int i =0;i<10;i++) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk2.get();

        }
    }
}