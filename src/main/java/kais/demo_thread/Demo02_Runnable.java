package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 20:33
 */
//创建线程方式2： 实现runnable接口，重写run()方法，执行线程需要丢入runnable接口实现类，调用start方法
public class Demo02_Runnable implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i < 1000;i ++){
            System.out.println("11111111111");
        }
    }

    public static void main(String[] args) {
        Demo02_Runnable runnable = new Demo02_Runnable();
        Thread thread = new Thread(runnable);
        thread.start();
        for(int i = 0;i < 1000;i ++){
            System.out.println("222222222222222");
        }
    }
}
