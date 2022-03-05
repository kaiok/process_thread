package kais.mytest;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kais
 * @date 2022.03.03. 21:46
 */
public class TestOh {

    private ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        final TestOh test = new TestOh();

        //Runnable是一个函数式接口，仅包含一个抽象方法run
            /*new Thread(){
            public void run() {
            test.insert(Thread.currentThread());
        }}.start();*/
        new Thread(() -> test.insert(Thread.currentThread())).start();

        new Thread(() -> test.insert(Thread.currentThread())).start();
    }

    public void insert(Thread thread) {
        Lock lock = new ReentrantLock(); //注意这个地方
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
        // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }

}
