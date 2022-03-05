package kais.JUC;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kais
 * @date 2022.03.03. 23:22
 * 1.使用读写锁(ReentrantReadWriteLock)实现两个线程同时进行读操作，提升读操作的效率
 * 2.两个线程并行运行
 */
public class ReadTest02 {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ReadTest02 readTest02 = new ReadTest02();

        new Thread(() -> readTest02.get(Thread.currentThread())).start();

        new Thread(() -> readTest02.get(Thread.currentThread())).start();

    }

    public void get(Thread thread){

        rwl.readLock().lock();

        try{
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 50){
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        }finally {
            rwl.readLock().unlock();
        }
    }

}
