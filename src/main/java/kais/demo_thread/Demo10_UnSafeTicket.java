package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.13. 21:55
 */
//线程同步--不安全的买票，票数出现负数
public class Demo10_UnSafeTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "Me").start();
        new Thread(buyTicket, "You").start();
        new Thread(buyTicket, "HuangNiu").start();
    }
}

class BuyTicket implements Runnable {
    //票
    private int ticketNums = 10;
    boolean flag = true;  //外部停止方式

    @Override
    public void run() {
        //买票
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized 同步方法，锁的是this（即调用这个方法的对象）
    private synchronized void buy() throws InterruptedException {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        //模拟延时
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
    }
}
