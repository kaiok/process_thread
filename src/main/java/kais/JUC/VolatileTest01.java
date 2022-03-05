package kais.JUC;

/**
 * @author kais
 * @date 2022.03.04. 11:13
 * volatile保证共享变量的可见性
 *
 * 1.那么当线程2更改了stop变量的值之后，但是还没来得及写入主存当中，线程2转去做其他事情了，那么线程1由于不知道线程2对stop变量的更改，
 *   因此还会一直循环下去。
 *
 * 2.但是用volatile修饰之后就变得不一样了：
 * 　第一：使用volatile关键字会强制将修改的值立即写入主存；
 *　 第二：使用volatile关键字的话，当线程2进行修改时，会导致线程1的工作内存中缓存变量stop的缓存行无效（反映到硬件层的话，
 *        就是CPU的L1或者L2缓存中对应的缓存行无效）；
 * 　第三：由于线程1的工作内存中缓存变量stop的缓存行无效，所以线程1再次读取变量stop的值时会去主存读取。
 * 　　  那么在线程2修改stop值时（当然这里包括2个操作，修改线程2工作内存中的值，然后将修改后的值写入内存），
 *         会使得线程1的工作内存中缓存变量stop的缓存行无效，然后线程1读取时，发现自己的缓存行无效，它会等待缓存行对应的主存地址被更新之后，
 *         然后去对应的主存读取最新的值。
 * 　　  那么线程1读取到的就是最新的正确的值
 */
public class VolatileTest01 {

    public static void main(String[] args) {
        //线程一
        boolean stop = false;
        while(!stop){
            doSomething();
        }

        //线程二
        stop = true;
    }

    private static void doSomething() {
    }

}
