//For a given sorted array and a target number, find the first index of this number in O(logn)
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int classicalBinarySearch (int[] nums, int target) {
        // write your code here
        if (nums==null || nums.length==0){
            return -1;
        }
        int start=0, end=nums.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(nums[mid]==target){
                end=mid;
            }else if(nums[mid]<target){
                start=mid;
            }else{
                end=mid;
            }
        }
        if(nums[start]==target){
            return start;
        }
        if(nums[end]==target){
            return end;
        }
        return -1;
    }
}
