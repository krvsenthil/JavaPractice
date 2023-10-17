package core;

import java.util.HashMap;

public class TwoSum {
    private  static void run(){
        System.out.println("My Search Engine");
        Label:// test message or label
        test://www.goolge.com
        return;
    }
    public static void main(String[] args) {
        run();
        String s = "hello";
        String s1 = "hel";
        String s2 = "lo";
        Long a = 111L;
        Long b = 111L;

        System.out.println(a == b);

        StringBuilder builder = new StringBuilder("hello");
        StringBuilder builder1 = new StringBuilder("hello");

        System.out.println((s1 + s2) == s);
        System.out.println(s.equals(builder)); //false
        System.out.println(builder.toString().equals(builder1.toString()));//true

        int[] nums = {2,7,11,15,8,1};
        int target = 9;
       /* for(int i=0;i<nums.length;i++){
            for(int j = i+1; j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    System.out.println(" Res:->"+nums[i]+"-"+nums[j]);
                }
            }
        }*/
        HashMap map = new HashMap();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target - nums[i])){
                System.out.println((target-nums[i])+"-"+nums[i]);
            }else{
                map.put(nums[i], i);
            }
            System.out.println(map.toString());
        }
    }
}
