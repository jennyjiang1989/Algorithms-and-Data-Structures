//Given an array of integers, find two numbers such that they add up to a specific target number.
//The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
//Please note that your returned answers (both index1 and index2) are zero-based.
//HashMap: time o(n) space o(n)
//Sort+2P: time o(nlogn) space o(1)
public class Solution {
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int[] result=new int[2];
        if(numbers==null||numbers.length==0){
            return result;
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])){
                result[0]=map.get(target-numbers[i]);
                result[1]=i;
                return result;
            }else{
                map.put(numbers[i],i);
            }
        }
        return result;
    }
}

//Given an array of integers that is already sorted in ascending order
//Please note that your returned answers (both index1 and index2) are NOT zero-based.
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        int[] result=new int[2];
        if(nums==null||nums.length==0){
            return result;
        }
        int i=0,j=nums.length-1;
        while(i<j){
            if(nums[i]+nums[j]==target){
                result[0]=i+1;
                result[1]=j+1;
                return result;
            }else if(nums[i]+nums[j]<target){
                i++;
            }else{
                j--;
            }
        }
        return result;
    }
}
