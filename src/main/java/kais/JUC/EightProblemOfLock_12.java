package kais.JUC;

import java.util.concurrent.TimeUnit;

/**
 * @author kais
 * @date 2022.03.07. 14:40
 */
public class EightProblemOfLock_12 {


    public static synchronized void sendSMS() throws Exception{
        //短信方法停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSMS");
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println("sendEmail");
    }

    public void getHello(){
        System.out.println("getHello");
    }

    //一个对象里面如果有多个 synchronized 方法，某一个时刻内，只要一个线程去调用其中的 一个 synchronized 方法了，
    // 其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些 synchronized 方法
    public static void main(String[] args) throws Exception{

        EightProblemOfLock_12 phone = new EightProblemOfLock_12();
        EightProblemOfLock_12 phone01 = new EightProblemOfLock_12();


        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            try {
                phone.sendEmail();
//                phone.getHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();
    }


}
