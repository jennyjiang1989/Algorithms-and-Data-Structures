//Given a sequence of integers, find the longest increasing subsequence (LIS).
//You code should return the length of the LIS.
//Input: [4,2,4,5,3,7]   Output: 4  Explanation: LIS is [2,4,5,7] 不一定连续

public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        //f[i]表示从某个位置 跳到 下标为i的位置 的LIS
        int[] f=new int[nums.length];
        int max=0;
        for(int i=0;i<nums.length;i++){
            f[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    f[i]=f[i]>f[j]+1?f[i]:f[j]+1;
                }
            }
            if(f[i]>max){
                max=f[i];
            }
        }
        return max;
    }
}
