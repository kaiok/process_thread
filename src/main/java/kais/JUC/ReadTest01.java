package kais.JUC;

/**
 * @author kais
 * @date 2022.03.03. 22:14
 * 使用synchronized实现读操作
 */
public class ReadTest01 {

    public static void main(String[] args) {

        ReadTest01 readTest = new ReadTest01();

        //get方法如果不加synchronized关键字，那么两个线程会并行运行
        new Thread(() -> readTest.get(Thread.currentThread())).start();

        new Thread(() -> readTest.get(Thread.currentThread())).start();

    }

    //System.currentTimeMillis();获取系统此刻毫秒数
    //设置1ms的时间间隔，模拟线程读操作
    public synchronized void get(Thread thread){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 10){
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }
}
