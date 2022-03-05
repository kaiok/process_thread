package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 20:55
 */
public class Demo03_Concurrent implements Runnable{

    //多线程同时操作同一个对象
    //抢火车票的例子，可能存在两个人都抢到一张票的情况
    //发现问题：多个线程操作同一个资源的情况下，线程不安全，数据会出现紊乱 (并发问题)
    private int ticketNums = 20;

    @Override
    public void run() {
        while (true) {
            if (ticketNums < 0) {
                break;
            }
            //模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //输出谁拿到了那一张票，票数减一
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNums-- + "票");
        }
    }
    public static void main(String[] args) {
        Demo03_Concurrent concurrent = new Demo03_Concurrent();
        new Thread(concurrent, "张三").start();
        new Thread(concurrent, "李四").start();
        new Thread(concurrent, "王五").start();
    }
}
