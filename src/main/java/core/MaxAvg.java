package core;

public class MaxAvg {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k = 2;
        int sum = 0;
        for(int i = 0; i < k; i++)
            sum += nums[i];
        //System.out.println("Sum->"+sum);
        int maxSum = sum;
        int[] res = new int[2];
        // Compute sum of remaining subarrays
        for(int i = k; i < nums.length; i++) {
            System.out.println("I->"+i+"T->"+(i-k));
            sum = sum + nums[i] - nums[i - k];
            //System.out.println("num->"+nums[i]+"<--nums-->"+nums[i - k]+"Sum->"+sum);
            if(sum > maxSum){
                res[0] = (i - k)+1;
                res[1] = i+1;
            }
            maxSum = Math.max(maxSum, sum);
            //System.out.println("MaxSum:-->"+maxSum);
        }
        System.out.println("Result:-->"+(double) maxSum / k);
        for(int m = res[0]; m < res[1]; m++){
            System.out.println("Final-->"+nums[m]);
        }
    }
}
