package kais.JUC;

import java.util.concurrent.*;

/**
 * @author kais
 * @date 2022.03.08. 19:56
 * 线程池的创建--案例：火车站3个售票口，10个用户买票
 */
public class ThreadPoolDemo_19 {

    public static void main(String[] args) {

        //定时线程次：线程数量为3-->窗口数为3
        ExecutorService threadService = new ThreadPoolExecutor(3,
                3,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try{
            //10个人买票
            for(int i = 1;i <= 10;i ++){
                threadService.execute(() -> {

                    try{
                        System.out.println(Thread.currentThread().getName() + "窗口，开始卖票");
                        Thread.sleep(5000);
                        System.out.println(Thread.currentThread().getName() + "窗口卖票结束");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadService.shutdown();
        }

    }

}
