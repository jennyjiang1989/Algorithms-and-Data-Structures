//Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // write your code here
        if(nums==null||nums.length==0){
            return;
        }
        int left=0,right=0;
        while(right<nums.length){
            if(nums[right]!=0){
                int temp=nums[right];
                nums[right]=nums[left];
                nums[left]=temp;
                left++;
            }
            right++;
        }
    }
}
