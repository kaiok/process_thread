package kais.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kais
 * @date 2022.03.05. 20:56
 * A 线程打印 5 次 A，B 线程打印 10 次 B，C 线程打印 15 次 C,按照 此顺序循环 10 轮
 */
public class ThreadDispatch07 {

    //0->A  1->B  2->C
    private int number = 0;
    private Lock lock = new ReentrantLock();
    //声明钥匙A、B和C
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public static void main(String[] args) {

        ThreadDispatch07 threadDispatch07 = new ThreadDispatch07();

        new Thread(() -> {
            for(int i = 0;i < 10;i ++){
                threadDispatch07.printA(i);
            }
        },"线程A").start();
        new Thread(() -> {
            for(int i = 0;i < 10;i ++){
                threadDispatch07.printB(i);
            }
        },"线程B").start();
        new Thread(() -> {
            for(int i = 0;i < 10;i ++){
                threadDispatch07.printC(i);
            }
        },"线程C").start();

    }


    //打印A
    public void printA(int j){
        try{
            lock.lock();
            while(number != 0){
                conditionA.await();
            }
            System.out.print(Thread.currentThread().getName() + "输出A，第" + j + "轮打印：");
            for(int i = 0;i < 5;i ++){
                System.out.print("A  ");
            }
            System.out.println();
            //A打印完毕，通知B
            number = 1;
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //打印B
    public void printB(int j){
        try{
            lock.lock();
            while(number != 1){
                conditionB.await();
            }
            System.out.print(Thread.currentThread().getName() + "输出B，第" + j + "轮打印：");
            for(int i = 0;i < 10;i ++){
                System.out.print("B  ");
            }
            System.out.println();
            //B打印完毕，通知C
            number = 2;
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //打印C
    public void printC(int j){
        try{
            lock.lock();
            while(number != 2){
                conditionC.await();
            }
            System.out.print(Thread.currentThread().getName() + "输出C，第" + j + "轮打印：");
            for(int i = 0;i < 15;i ++){
                System.out.print("C  ");
            }
            System.out.println();
            //B打印完毕，通知C
            number = 0;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
