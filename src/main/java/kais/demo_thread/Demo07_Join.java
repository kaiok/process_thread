package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 21:55
 */
//线程强制执行
public class Demo07_Join implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i < 200;i ++){
            System.out.println("线程VIP来了～～");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Demo07_Join join = new Demo07_Join();
        Thread thread = new Thread(join);
        thread.start();

        //当i等于20，便让我们自己线程优先执行完，之前和main主线程是交替运行的
        for(int i = 0;i < 100;i ++){
            if(i == 20){
                thread.join();
            }
            System.out.println("main" + i);
        }
    }
}
