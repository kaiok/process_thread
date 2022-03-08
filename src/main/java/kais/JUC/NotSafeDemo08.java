package kais.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kais
 * @date 2022.03.05. 21:54
 * 集合的线程安全
 */
public class NotSafeDemo08 {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();

        //多个线程同时对集合进行修改
        //increments modCount
        for(int i = 0;i < 100;i ++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
            },"线程" + i).start();
        }

    }

}
