//Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//Find the minimum element.

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if(nums==null||nums.length==0){
            return -1;
        }
        int start=0,end=nums.length-1;
        int target=nums[end];
        //find the first element <= target
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(nums[mid]<=target){
                end=mid;
            }else{
                start=mid;
            }
        }
        return Math.min(nums[start],nums[end]);
    }
}
