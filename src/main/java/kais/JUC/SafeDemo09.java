package kais.JUC;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author kais
 * @date 2022.03.05. 22:48
 * Vector是矢量队列，底层的add方法有synchronized修饰，因此在多线程环境下是安全的
 *
 */
public class SafeDemo09 {

    public static void main(String[] args) {

        List<Object> vector = new Vector<>();

        //多个线程同时对集合进行修改
        //increments modCount
        for(int i = 0;i < 100;i ++){
            new Thread(() -> {
                vector.add(UUID.randomUUID().toString());
            },"线程" + i).start();
        }

    }

}
