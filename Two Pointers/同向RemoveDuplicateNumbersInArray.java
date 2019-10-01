//同向双指针
//Given an array of integers, remove the duplicate numbers in it. Return the total number of the unique numbers.
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int slow=0;
        int fast=1;
        while(fast<nums.length){
            if(nums[slow]==nums[fast]){
                fast++;
            }else{
                slow++;
                nums[slow]=nums[fast];
                fast++;
            }
        }
        return slow+1;
    }
}
