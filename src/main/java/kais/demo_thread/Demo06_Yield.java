package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 21:48
 */
//线程礼让
//    + 礼让线程，让当前正在执行的线程暂停，但不阻塞
//            + 将线程从运行状态转为就绪状态
//            + 让cpu重新调度，礼让不一定成功！由cpu决定
public class Demo06_Yield {

    public static void main(String[] args) {
        MyYield yield = new MyYield();
        new Thread(yield,"a").start();
        new Thread(yield,"b").start();
    }

}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束运行");
    }
}