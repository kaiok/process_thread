package kais.JUC;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author kais
 * @date 2022.03.08. 21:10
 * Fork/Join 框架的使用
 * 任务：生成一个计算任务，计算1+2+3...+1000，每100个数切分一个子任务
 */
public class ForkJoinDemo_20 extends RecursiveTask<Long> {

    private int start;
    private int end;
    private long sum;

    //构造函数
    public ForkJoinDemo_20(int start, int end){
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        //定义任务
        ForkJoinDemo_20 taskExample = new ForkJoinDemo_20(1, 1000);
        //定义执行对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //加入任务执行
        ForkJoinTask<Long> submit = forkJoinPool.submit(taskExample);

        //输出结果
        try {
            System.out.println(submit.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            forkJoinPool.shutdown();
        }
    }

    @Override
    protected Long compute() {

        System.out.println("任务" + start + "<-->" + end + "累加开始");
        //大于 100 个数相加切分，小于直接加
        if(end - start <= 100){
            for(int i = start;i <= end;i ++){
                //累加
                sum += i;
            }
        }else{
            //切分为2块
            int middle = start + 100;
            //递归调用，切分为2个小任务
            ForkJoinDemo_20 taskExample1 = new ForkJoinDemo_20(start, middle);
            ForkJoinDemo_20 taskExample2 = new ForkJoinDemo_20(middle + 1, end);
            //执行异步
            taskExample1.fork();
            taskExample2.fork();
            //同步阻塞获取执行结果，Join 方法的主要作用是阻塞当前线程并等待获取结果
            sum = taskExample1.join() + taskExample2.join();
        }
        return sum;
    }
}
