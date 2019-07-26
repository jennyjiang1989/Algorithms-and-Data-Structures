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

//Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. 
//Please return the number of pairs.
public class Solution {
    /**
     * @param nums an array of integer
     * @param target an integer
     * @return an integer
     */
    public int twoSum6(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            }
            else if (nums[left] + nums[right] > target) {
                right--;
            }
            else {
                count++;
                left++;
                right--;
                //去重 unique pair
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
        return count;
    }
}

//Find how many pairs in the array that their sum is less than or equal to target. Return the number of pairs.
//不用去重 没说Unique
public class Solution {
	public int twoSum(int[] nums, int target){
		if(nums==null||nums.length<2){
			return 0;
		}
		Arrays.sort(nums);
		int count=0;
		int i=0,j=nums.length-1;
		while(i<j){
			if(nums[i]+nums[j]<=target){
				count=count+j-i;//不是只有一个
				i++;
			}else{
				j--;
			}
		}
		return count;
	}
}
