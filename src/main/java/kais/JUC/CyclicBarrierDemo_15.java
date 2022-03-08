package kais.JUC;

import java.util.concurrent.CyclicBarrier;

/**
 * @author kais
 * @date 2022.03.07. 18:51
 */
public class CyclicBarrierDemo_15 {

    private final static int NUMBER = 7;

    public static void main(String[] args) {

        //函数式接口
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐" + NUMBER + "颗龙珠，现在召唤神龙！");
        });

        //创建7个线程去寻找龙珠
        for(int i = 1;i <= 7;i ++){
            new Thread(() -> {
                try{
                    System.out.println("正在寻找" + Thread.currentThread().getName());
                    Thread.sleep(9000);
                    System.out.println("已找到" + Thread.currentThread().getName());
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },"龙珠" + i + "号").start();
        }
    }

}
