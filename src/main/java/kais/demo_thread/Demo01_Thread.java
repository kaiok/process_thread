package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 20:23
 */
//创建线程方式一：继承Thread类，重写run方法，调用start开启线程
//总结： 注意，线程开启不一定立即执行，由CPU调度
public class Demo01_Thread extends Thread{

    //run方法线程体
    @Override
    public void run() {
        for(int i = 0;i < 1000;i ++){
            System.out.println("11111111111");
        }
    }

    //main方法是主线程
    public static void main(String[] args) {
        Demo01_Thread thread = new Demo01_Thread();
        //调用start方法开启线程
        thread.start();
        for(int i = 0;i < 1000;i ++){
            System.out.println("222222222");
        }
    }

}
