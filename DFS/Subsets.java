//Given a set of distinct integers, nums, return all possible subsets (the power set).
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
        results.add(new ArrayList<Integer>(subset));
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
