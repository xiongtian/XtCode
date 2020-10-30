package com.study.day.test201001;

/**
 * @author xiongtian
 * @create 2020/10/1-2:43
 */
public class test01 {


    public static void main(String[] args) {
        Clerk1 clerk1 = new Clerk1();
        Producer1 productor = new Producer1(clerk1);
        Consumer1 consumer = new Consumer1(clerk1);

        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }


}

class Clerk1{
    private int product;

    public synchronized void  sale(){
        while (product<=0){
            System.out.println("缺货中！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ --product);
        this.notifyAll();
    }

    public synchronized  void get(){
        while (product >=1){
            System.out.println("商品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();
    }

}

class Producer1 implements Runnable{
    private Clerk1 clerk;

    public Producer1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    public void run() {
        for (int i= 0;i<10;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}
class Consumer1 implements Runnable{

    private Clerk1 clerk;

    public Consumer1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    public void run() {
        for (int i= 0;i<10;i++){
            clerk.sale();
        }
    }
}