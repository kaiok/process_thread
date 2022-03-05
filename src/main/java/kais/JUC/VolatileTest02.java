package kais.JUC;

/**
 * @author kais
 * @date 2022.03.04. 11:20
 * 仅仅volatile关键字无法保证共享变量的原子性
 *
 * 1.可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。
 *
 * 2.在前面已经提到过，自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。
 *      那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致下面这种情况出现：
 *
 * ---假如某个时刻变量inc的值为10，
 *
 * 　　1.线程1对变量进行自增操作，线程1先读取了变量inc的原始值，然后线程1被阻塞了；
 *
 * 　　2.然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会
 * 导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，
 * 最后写入主存。
 *
 * 　　3.然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的
 * 值为11，然后将11写入工作内存，最后写入主存。
 *
 * 　　4.那么两个线程分别进行了一次自增操作后，inc只增加了1。
 *
 * 　　5.解释到这里，可能有朋友会有疑问，不对啊，前面不是保证一个变量在修改volatile变量时，会让缓存行无效吗？然后其他线程去读就会读到新的值，
 * 对，这个没错。这个就是上面的happens-before规则中的volatile变量规则，但是要注意，线程1对变量进行读取操作之后，被阻塞了的话，并没有对inc值
 * 进行修改。然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没有进行修改，所以线程2根本就不会看到修改的值。
 *
 * 　　6.根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的
 */
public class VolatileTest02 {

    public volatile int inc = 0;

    public void increase(){
        inc ++;
    }

    public static void main(String[] args) {

        VolatileTest02 test02 = new VolatileTest02();

        for(int i = 0;i < 10; i ++){
            new Thread(){
                public void run(){
                    for(int j = 0;j < 10000;j ++){
                        test02.increase();
                    }
                }
            }.start();
        }

        //保证前面的线程都执行完了
        while(Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(test02.inc);
    }

}
