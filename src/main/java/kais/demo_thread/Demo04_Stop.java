package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.11. 21:30
 */
//线程停止
//+ 建议线程正常停止--利用次数，不建议死循环
//+ 不要使用stop或者destroy等过时或者JDK不建议使用的方法
public class Demo04_Stop implements Runnable{
    //设置一个标识位
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("run...Thread" + i++);
        }
    }
    //设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }
    public static void main(String[] args) {
        Demo04_Stop thread_stop = new Demo04_Stop();
        new Thread(thread_stop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main主线程:" + i);
            if(i == 888){
                //调用stop方法，让线程停止
                thread_stop.stop();
                System.out.println("该线程停止了!");
            }
        }
    }
}
