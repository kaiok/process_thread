package kais.JUC;

/**
 * @author kais
 * @date 2022.03.04. 11:39
 * 使用synchronized保证共享变量操作的原子性,inc每次都自增成功，结果为100000
 */
public class VolatileTest03 {

        public int inc = 0;

        public synchronized void increase() {
            inc++;
        }

        public static void main(String[] args) {
            final VolatileTest03 test = new VolatileTest03();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<10000;j++)
                            test.increase();
                    };
                }.start();
            }

            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }


}
