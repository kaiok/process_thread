package kais.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author kais
 * @date 2022.03.07. 17:33
 */
public class CallableDemo_13 {

    static class Thread1 implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "进入run方法");
        }
    }

    static class Thread2 implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "进入call方法");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "继续运行");
            return System.currentTimeMillis();
        }
    }

    public static void main(String[] args) throws Exception{
        Runnable runnable = new Thread1();
        // 将 FutureTask 对象提供给 Thread 的构造函数以创建 Thread 对象
        // 通过为FutureTask构造函数提供 Callable 来创建 FutureTask
        Callable callable = new Thread2();
        FutureTask futureTask = new FutureTask<Long>(callable);
        new Thread(futureTask,"线程二").start();

        for(int i = 0;i < 10;i ++){
            Long result = (Long) futureTask.get();
            System.out.println(result);
        }

        new Thread(runnable,"线程一").start();
    }


}
