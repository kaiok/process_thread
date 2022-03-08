package kais.JUC;

/**
 * @author kais
 * @date 2022.03.03. 23:53
 * 使用synchronized实现线程通讯
 * 面试题：两个线程，一个线程对当前数值加一，另一个线程对当前数值减一，要求线程间通讯
 * volatile实现线程交替加减
 */
public class ThreadDispatch05 {

    //加减对象
    private int number = 0;

    public static void main(String[] args) {
        ThreadDispatch05 threadDispatch05 = new ThreadDispatch05();

        new Thread(() -> {
            for(int i = 0;i < 5; i ++){
                threadDispatch05.increment();
            }
        },"线程A").start();

        new Thread(() -> {
            for(int i = 0;i < 5 ; i ++){
                threadDispatch05.decrement();
            }
        },"线程B").start();
    }

    //加一操作
    public synchronized void increment(){
        try{
            while(number != 0){
                this.wait();
            }
            number ++;
            System.out.println("---" + Thread.currentThread().getName() + " 加一成功，" + "值为" + number);
            notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //减一操作
    public synchronized void decrement(){
        try{
            while(number == 0){
                this.wait();
            }
            number --;
            System.out.println("---" + Thread.currentThread().getName() + " 减一成功，" + "值为" + number);
            notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
