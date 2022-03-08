package kais.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kais
 * @date 2022.03.05. 20:31
 * 面试题：两个线程，一个线程对当前数值加一，另一个线程对当前数值减一，要求线程间通讯
 * Lock实现线程交替加减
 */
public class ThreadDispatch06 {

    //加减对象
    private int number = 0;

    //声明锁
    private Lock lock = new ReentrantLock();

    //声明钥匙
    private Condition condition = lock.newCondition();


    public static void main(String[] args) {

        ThreadDispatch06 threadDispatch06 = new ThreadDispatch06();

        new Thread(() -> {
            for(int i = 0;i < 5; i ++){
                threadDispatch06.decrement();
            }
        }).start();

        new Thread(() -> {
            for(int i = 0;i < 5;i ++){
                threadDispatch06.increment();
            }
        }).start();

    }

    //加一
    public void increment(){
        try{
            lock.lock();
            while(number != 0){
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName() + "加一操作成功" + "，值为：" + number);
            //唤醒处于等待状态的执行减一操作的线程
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //减一
    public void decrement(){
        try{
            lock.lock();
            while(number == 0){
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName() + "减一操作成功" + "值为：" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
