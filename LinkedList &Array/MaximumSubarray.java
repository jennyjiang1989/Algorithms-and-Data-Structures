class Solution {
    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int maxSubarray=Integer.MIN_VALUE;
        int prefixSum=0;
        int minPrefixSum=0;
        for(int i=0;i<nums.length;i++){
            prefixSum+=nums[i];
            maxSubarray=Math.max(maxSubarray,prefixSum-minPrefixSum);
            minPrefixSum=Math.min(minPrefixSum,prefixSum);
        }
        return maxSubarray;
    }
}
