package kais.JUC;

import java.util.concurrent.Semaphore;

/**
 * @author kais
 * @date 2022.03.07. 22:24
 * 抢车位，10部汽车1个听车位
 */
public class SemaphoreDemo_16 {

    public static void main(String[] args) throws Exception{

        //定义3个停车位
        Semaphore semaphore = new Semaphore(3);
        //模拟10部汽车
        for(int i = 1;i <= 10;i ++){
            Thread.sleep(100);
            //停车
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "找车位 ing");
                    //使用 acquire 方法获得许可证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "汽车停车成功!");
                    Thread.sleep(10000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    System.out.println(Thread.currentThread().getName() + "溜了溜了");
                    //release 方法释放许可
                    semaphore.release();
                }
            }, "汽车" + i).start();
        }
    }

}
