package kais.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @author kais
 * @date 2022.03.07. 18:05
 * 6位同学陆续离开教室后值班同学才可以关门
 */
public class CountDownLatchDemo_14 {

    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 0;i < 6;i ++){
            new Thread(() -> {
                try{
                    System.out.println(Thread.currentThread().getName() + "离开了");
                    // 调用 countDown 方法会将计数器减 1
                    // 计数器减一，不会阻塞
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },"同学" + i).start();
        }

        // 主线程await休息，调用 await 方法时，这些线程会阻塞
        System.out.println("主线程休息");
        countDownLatch.await();

        // 当计数器的值变为 0 时， await 方法阻塞的线程会被唤醒，继续执行
        // 全部离开后自动唤醒主线程

        System.out.println("全部离开了，现在的计数器为：" + countDownLatch.getCount() + ",主线程为：" + Thread.currentThread().getName());

    }

}
