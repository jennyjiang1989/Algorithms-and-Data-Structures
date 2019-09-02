//Given a rotated sorted array, recover it to sorted array in-place.
//Example1:[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if(nums==null||nums.size()==0){
            return;
        }
        int i=0,j=1;
        while(j<nums.size()){
            if(nums.get(i)>nums.get(j)){
                break;
            }
            i++;
            j++;
        }
        reverse(nums,0,i);
        reverse(nums,j,nums.size()-1);
        reverse(nums,0,nums.size()-1);
    }
    private void reverse(List<Integer> nums, int start, int end){
        for(int i=start,j=end;i<j;i++,j--){
            int temp=nums.get(i);
            nums.set(i,nums.get(j));
            nums.set(j,temp);
        }
    }
}
