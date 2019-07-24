//Given an array of n integers, and a moving window(size k), move the window at each iteration from the start of the array
//Find the sum of the element inside the window at each moving.
public class Solution {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if(nums==null||nums.length<k||k<=0){
            return new int[0];
        }
        int length=nums.length-k+1;
        int[] result=new int[length];
        int firstSum=nums[0];
        for(int i=1;i<k;i++){
            firstSum+=nums[i];
        }
        result[0]=firstSum;
        for(int i=1;i<length;i++){
            result[i]=result[i-1]-nums[i-1]+nums[i+k-1];
        }
        return result;
    }
}
