//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
//Find all unique(要去重) triplets in the array which gives the sum of zero.
public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
     //a+b+c=0 => b+c=-a
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> results=new ArrayList<>();
        if(numbers==null||numbers.length<3){
            return results;
        }
        Arrays.sort(numbers);
        //1 2 3 4 -2 -3 -4 -5 length=8
        //0 1 2 3  4  5(!)  6  7 
        for(int i=0;i<numbers.length-2;i++){
            //去重
            if(i!=0&&numbers[i]==numbers[i-1]){
                continue;
            }
            int target=-numbers[i];
            int start=i+1;
            int end=numbers.length-1;
            twoSum(numbers,start,end,target,results);
        }
        return results;
    }
    private void twoSum(int[] numbers, int start, int end, int target, List<List<Integer>> results){
        while(start<end){
            if(numbers[start]+numbers[end]==target){
                ArrayList<Integer> oneResult=new ArrayList<>();
                oneResult.add(-target);
                oneResult.add(numbers[start]);
                oneResult.add(numbers[end]);
                results.add(oneResult);
                start++;
                end--;
                while(start<end&&numbers[start]==numbers[start-1]){
                    start++;
                }
                 while(start<end&&numbers[end]==numbers[end+1]){
                    end--;
                }
            }else if(numbers[start]+numbers[end]<target){
                start++;
            }else{
                end--;
            }
        }
    }
}

//3 sum closest to target
//Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
//Return the sum of the three integers.
public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        if(numbers==null||numbers.length<3){
            return 0;
        }
        Arrays.sort(numbers);
        int closestSum=Integer.MAX_VALUE;
        for(int i=0;i<numbers.length-2;i++){
            int left=i+1;
            int right=numbers.length-1;
            while(left<right){
                int currentSum=numbers[i]+numbers[left]+numbers[right];
                if(Math.abs(target-currentSum)<Math.abs(target-closestSum)){
                    closestSum=currentSum;
                }
                //先计算 再 比较移动
                if(currentSum<target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return closestSum;
    }
}
