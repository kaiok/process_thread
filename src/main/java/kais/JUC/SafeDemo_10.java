package kais.JUC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author kais
 * @date 2022.03.05. 23:22
 */
public class SafeDemo_10 {

    public static void main(String[] args) {

        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for(int i = 0;i < 100;i ++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }).start();
        }

    }

}
