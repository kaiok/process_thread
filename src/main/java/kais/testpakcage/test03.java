package kais.testpakcage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author kais
 * @date 2022.03.04. 16:47
 * 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
 */
public class test03 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arrays = new int[N];
        for(int i = 0;i < N; i ++){
            arrays[i] = scanner.nextInt();
        }

        System.out.println(returnSum02(arrays));

    }

    public static int returnSum(int[] arrays){
        int[] test = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};

        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0;i < test.length;i ++){
            hashSet.add(test[i]);
        }
        int startSize = hashSet.size();

        for(int i = 0;i < arrays.length;i ++){
            if(!hashSet.contains(arrays[i])){
                hashSet.add(arrays[i]);
            }
        }
        int endSize = hashSet.size();

        return (arrays.length - (endSize - startSize));
    }

    public static int returnSum02(int[] arrays){

        int temp = 0;
        int[] test = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};


        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for(int i = 0;i < test.length ; i ++){
            arrayList.add(test[i]);
        }

        //统计数组中质数的个数
        for(int i = 0;i < arrays.length;i ++){
            if(arrayList.contains(arrays[i])){
                arrayList1.add(arrays[i]);
                temp ++;
            }
        }

        //计算非质数中不可以被其它数整除的数的个数
        for(int i = 0;i < arrays.length;i ++){
            if(!arrayList.contains(arrays[i]) && arrays[i] == 1){
                continue;
            }else if(!arrayList.contains(arrays[i])){
                boolean flag = false;
                for(int j = 0;j < arrayList1.size();j ++){
                    if(arrays[i] % arrayList1.get(j) == 0){
                        flag = true;
                    }
                }
                //当arrayList1中可以被数组中的数除尽时就不会自增
                if(!flag){
                    temp ++;
                }
            }
        }
        return temp;
    }
}
