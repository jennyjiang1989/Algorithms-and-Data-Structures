//Given an integer array, find a subarray where the sum of numbers is zero. 
//Your code should return the index of the first number and the index of the last number.
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        List<Integer> result=new ArrayList<>();
        if(nums==null||nums.length==0){
            return result;
        }
        //prefixSum -> index
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int prefixSum=0;
        for(int i=0;i<nums.length;i++){
            prefixSum+=nums[i];
            if(map.containsKey(prefixSum)){
                result.add(map.get(prefixSum)+1);
                result.add(i);
                return result;
            }else{
                map.put(prefixSum,i);
            }
        }
        return result;
    }
}

//复杂度太高 
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if(nums==null||nums.length==0){
            return result;
        }
        if(nums.length==1&&nums[0]!=0){
        	return result;
        }
        if(nums.length==1&&nums[0]==0){
        	result.add(0);
        	result.add(0);
        	return result;
        }
        int[] prefixSum=new int[nums.length+1];
        prefixSum[0]=0;
        for(int i=1;i<=nums.length;i++){
            prefixSum[i]=prefixSum[i-1]+nums[i-1];
            for(int j=0;j<i;j++){
                int subArraySum=prefixSum[i]-prefixSum[j];
                if(subArraySum==0){
                    result.add(j);
                    result.add(i-1);
                    return result;
                }
            }
        }
        return result;
    }
}
