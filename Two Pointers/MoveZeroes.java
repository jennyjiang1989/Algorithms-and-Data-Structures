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
        int i=0,j=0;
        while(j<nums.length){
            if(nums[j]!=0){
                int temp=nums[j];
                nums[j]=nums[i];
                nums[i]=temp;
                i++;
            }
            j++;
        }
    }
}
