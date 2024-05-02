package lift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LiftDemo {
     static HashMap<Integer,Integer> liftMap = new HashMap<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Do you want to assign a lift : \n 1 for yes 0 for no : ");
            int choice = sc.nextInt();
            if(choice == 1){
                liftInit();
            }else{
                break;
            }
        }
    }
    public static void liftInit(){
        for(int i = 1;i<=5;i++){
            liftMap.put(i,0);

        }
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the starting point it must be between 0,10: ");
        int start = sc.nextInt();
        System.out.print("\nEnter the ending point it must be between 0,10 : ");
        int end = sc.nextInt();
        findLift2(start,end);
        System.out.println(liftMap);
    }
    public static void findLift2(int start,int end){
        HashMap<Integer,Integer> difference = new HashMap<>();
        for(int i = 1;i<=5;i++){
            difference.put(i,20);
        }
        for (Map.Entry<Integer, Integer> mapElement : liftMap.entrySet()) {
            int key = mapElement.getKey();
            int value = mapElement.getValue();
            int sum = 0;
            switch (key){
                case 5 :{
                    sum = Math.abs((value - start) + (end - start));
                }
                case 1,2 :{
                    if(value > 5){
                        sum +=  1 ;
                        if(start < 5){
                            sum+= Math.abs(start - 5);
                        }
                        if(end > 5){
                            sum+= 1;
                        }else{
                            sum += Math.abs(5-end) + 1;
                        }
                    }else{
                        if(start > 5){
                            sum+= 1;
                        }else{
                            sum+= Math.abs(start - value);
                        }

                        if(end > 5){
                            sum+= 1;
                        }
                        sum += Math.abs(end-5);
                    }
                }
                case 3,4 :{
                    if(value < 5){
                        sum +=  1 ;
                        if(start > 5){
                            sum+= Math.abs(end - start);
                        }
                        if(end > 5){
                            sum += Math.abs(5-end) + 1;
                        }else{
                            sum += 3;
                        }
                    }else{
                        if(start < 5){
                            sum+= 4;
                        }else{
                            sum+= Math.abs(start - value);
                        }

                        if(end > 5){
                            sum+= 1;
                        }
                        sum += Math.abs(start-5);
                    }
                }

            }
            difference.replace(key,sum);
        }
        int min = 20;
        int perfectKey = 0;
        for (Map.Entry<Integer, Integer> mapElement : difference.entrySet()) {
            int key = mapElement.getKey();
            int value = mapElement.getValue();
            if(value < min) {
                min = value;
                perfectKey = key;
            }
        }
        liftMap.put(perfectKey,end);
        System.out.println(liftMap);
        }

    public static void findLift(int start,int end){
        int min = 11;
        int perfectKey = 0;
        ArrayList<Integer> keyList = new ArrayList<>();
         for (Map.Entry<Integer, Integer> mapElement : liftMap.entrySet()) {
            int key = mapElement.getKey();
            int value = mapElement.getValue();
            int dif = Math.abs(start - value);
            if(dif < min){
                min = dif;
                perfectKey = key;
                keyList.clear();
            }else if(dif == min){
                keyList.add(perfectKey);
            }
        }
         keyList.add(0,perfectKey);
         if(keyList.size() > 1){
             perfectKey = findPerfectLift(keyList,start,end);
         }
        System.out.println(perfectKey);
         liftMap.replace(perfectKey,end);

        System.out.println(liftMap);
    }
    public static int findPerfectLift(ArrayList<Integer> keyList,int start,int end){
        int min = 11;
        int perfectKey = 0;
        if(start > end){
            for(int n : keyList){
                int val = liftMap.get(n);
                if(val < min){
                    min = val;
                    perfectKey = n;
                }
            }
            return  perfectKey;

        }
        int max = 0;
        if(start < end){
            for(int n : keyList){
                int val = liftMap.get(n);
                if(val > max){
                    max = val;
                    perfectKey = n;
                }
            }
            return perfectKey;
        }
        return -1;
    }
}

