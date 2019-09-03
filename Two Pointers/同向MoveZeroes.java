//同向双指针
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
        int oldIndex=0;//老数组
        int newIndex=0;//新数组
        while(oldIndex<nums.length){
            //非零元素往左移
            if(nums[oldIndex]!=0){
                int temp=nums[oldIndex];
                nums[oldIndex]=nums[newIndex];
                nums[newIndex]=temp;
                newIndex++;
            }
            oldIndex++;
        }
    }
}
