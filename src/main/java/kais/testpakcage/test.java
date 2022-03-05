package kais.testpakcage;

import java.util.Scanner;

/**
 * @author kais
 * @date 2022.03.04. 15:20
 */
public class test {

    //a-97  z-122   0-48  9-57
    public static void main(String[] args) {

        //记录最大长度
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        int[] arrays = new int[string.length()];
        for(int i = 0;i < string.length();i ++){
            arrays[i] = string.charAt(i);
        }

        int index = 0;
        int temp = 0;
        boolean flag = true;
        while(index != arrays.length - 1){
            if(flag){
                temp = 0;
            }
            //处理数组第一位元素
            if(index == 0 && arrays[index] >= 48 && arrays[index] <= 57){
                flag = false;
                temp ++;
                index ++;
            }else if(arrays[index] >= 48 && arrays[index] <= 57 && arrays[index] >= arrays[index - 1]){
                flag = false;
                if(temp == 0){
                    temp = 2;
                }else {
                    temp ++;
                }
            }else {
                flag = true;
            }
            //记录最大值
            if(temp > count){
                count = temp;
            }
            index ++;
        }

        if(arrays[index] >= 48 && arrays[index] <= 57 && arrays[index - 1] <= arrays[index]){
            temp ++;
        }
        //记录最大值
        if(temp > count){
            count = temp;
        }
        System.out.println(count);
    }
}
