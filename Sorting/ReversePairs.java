//Reverse pair is a pair of numbers (A[i], A[j]) such that A[i] > A[j] and i < j. 
//Given an array, return the number of reverse pairs in the array.

public class Solution {
    /**
     * @param A: an array
     * @return: total of reverse pairs
     */
    public long reversePairs(int[] nums) {
        // write your code here
        return mergeSort(nums,0,nums.length-1);
    }
    private int mergeSort(int[] nums, int low, int high){
        if(low>=high){
            return 0;
        }
        int mid=low+(high-low)/2;
        int result=mergeSort(nums,low,mid)+mergeSort(nums,mid+1,high);
        result+=merge(nums,low,high,mid);
        return result;
    }
    private int merge(int[] nums, int low, int high, int mid){
        int result=0;
        int[] merged=new int[high-low+1];
        int i=low, j=mid+1, index=0;
        while(i<=mid&&j<=high){
            if(nums[i]<=nums[j]){
                merged[index++]=nums[i++];
            }else{
                merged[index++]=nums[j++];
                result+=mid-i+1;
            }
        }
        while(i<=mid){
            merged[index++]=nums[i++];
        }
        while(j<=high){
            merged[index++]=nums[j++];
        }
        
        for(i=low,index=0;i<=high;i++,index++){
            nums[i]=merged[index];
        }
        return result;
    }
}
