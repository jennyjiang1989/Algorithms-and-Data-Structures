//Given a set of distinct integers, nums, return all possible subsets (the power set).
//深度优先搜索的空间耗费跟深度有关 宽度优先搜索的空间耗费跟宽度有关
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        if(nums==null){
            return results;
        }
        if(nums.length==0){
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        helper(new ArrayList<Integer>(),nums,0,results);
        return results;
    }
    //1. 递归的定义：在nums中找到所有以subset开头的集合，并放到results
    private void helper(ArrayList<Integer> subset, int[] nums, int startIndex, List<List<Integer>> results){
        //wrong: results.add(subset);
        results.add(new ArrayList<Integer>(subset));//deep copy 
        for(int i=startIndex;i<nums.length;i++){
            //[1]->[1,2]
            subset.add(nums[i]);
            //寻找所有以[1,2]开头的集合，并扔到results;
            helper(subset,nums,i+1,results);
            //[1,2]->[1] 回溯
            subset.remove(subset.size()-1);
        }
    }
    //3. 递归的出口
    // return;
}
//非递归算法
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        //1<<n is 2^n
        //each subset equals to an binary integer between 0 .. 2^n-1
        //0 -> 000 -> []
        //1 -> 001 -> [1]
        //2 -> 010 -> [2]
        //..
        //7 -> 111 -> [1,2,3]
        for(int i=0;i<(1<<n);i++){
            ArrayList<Integer> subset=new ArrayList<>();
            for(int j=0;j<n;j++){
                //check whether the jth digit in i's binary representation is 1
                if((i&(1<<j))!=0){
                    subset.add(nums[j]);
                }
            }
            results.add(subset);
        }
        return results;
    }
}
