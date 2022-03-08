package kais.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kais
 * @date 2022.03.07. 22:42
 * 使用 ReenTrantReadWriteLock实现对hashMap的读和写操作
 */
public class ReadAndWriteLock_17 {
    //volatile  保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值
    // (volatile关键字会强制将修改值立即写入内存，并且该公共变量在其它线程的缓存区内被标记为无效，
    // 一次其它线程想要使用该变量必须去内存中读取)，这新值对其他线程来说是立即可见的
    private volatile Map<String,Object> map = new HashMap<>();

    //创建读写锁对象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //放数据的方法
    public void putData(String key,Object value){
        //添加写锁
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            //暂停一会儿
            TimeUnit.MICROSECONDS.sleep(300);
            //放数据
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写完数据");

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //释放锁
            rwLock.writeLock().unlock();
        }
    }

    //读数据
    public Object readData(String key){
        //添加读锁
        rwLock.readLock().lock();
        Object result = null;
        try{
            System.out.println(Thread.currentThread().getName() + "正在读取数据" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + key);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException{
        ReadAndWriteLock_17 readAndWriteLock = new ReadAndWriteLock_17();

        //创建线程存放数据
        for(int i = 1; i <= 5;i ++){
            final int num = i;
            new Thread(() -> {
                //将key与value转换为String类型
                readAndWriteLock.putData(num + "",num + "");
            },"线程" + i).start();
        }

        TimeUnit.MICROSECONDS.sleep(300);

        //创建线程取数据
        for(int i = 1;i <= 5;i ++){
            final int num = i;
            new Thread(() -> {
                readAndWriteLock.readData(num + "");
            },"线程" + i).start();
        }
    }

}
