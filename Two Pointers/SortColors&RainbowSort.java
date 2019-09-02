//Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // write your code here
        if(nums==null||nums.length<=1){
            return;
        }
        int left=0;
        int right=nums.length-1;
        int i=0;
        while(i<=right){
            if(nums[i]==0){
                swap(nums,left,i);
                left++;
                i++;
            }else if(nums[i]==2){
                swap(nums,i,right);
                right--;
            }else{
                i++;
            }
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}

//Given an array of n objects with k different colors (numbered from 1 to k), 
//sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if(colors==null||colors.length==0){
            return;
        }
        rainbowSort(colors,0,colors.length-1,1,k);
    }
    private void rainbowSort(int[] colors, int left, int right, int colorFrom, int colorTo){
        if(colorFrom==colorTo){
            return;
        }
        if(left>=right){
            return;
        }
        int colorMid=(colorFrom+colorTo)/2;
        int l=left,r=right;
        while(l<=r){
            while(l<=r && colors[l]<=colorMid){
                l++;
            }
            while(l<=r && colors[r]>colorMid){
                r--;
            }
            if(l<=r){
                int temp=colors[l];
                colors[l]=colors[r];
                colors[r]=temp;
                l++;
                r--;
            }
        }
        //left r l right
        rainbowSort(colors,left,r,colorFrom,colorMid);
        rainbowSort(colors,l,right,colorMid+1,colorTo);
    }
}
