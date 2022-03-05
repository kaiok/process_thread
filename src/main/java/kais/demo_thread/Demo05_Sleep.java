package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 21:34
 */
//线程休眠
public class Demo05_Sleep implements Runnable{
    //票数
    private int ticketNums = 20;

    @Override
    public void run() {
        while(true){
            if(ticketNums <= 3){
                break;
            }
            //模拟网络延时，三个线程几乎抢不到同一张票
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->抢到第" + ticketNums-- + "票");
        }
    }

    public static void main(String[] args) {
        Demo05_Sleep sleep = new Demo05_Sleep();
        new Thread(sleep,"xiaoming").start();
        new Thread(sleep,"wang").start();
        new Thread(sleep,"kais").start();
    }
}
