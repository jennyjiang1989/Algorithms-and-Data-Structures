//Given a collection of distinct integers, return all possible permutations.
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        if(nums==null){
            return results;
        }
        ArrayList<Integer> list=new ArrayList<>();
        helper(list,nums,results);
        return results;
    }
    private void helper(ArrayList<Integer> list, int[] nums, List<List<Integer>> results){
        if(list.size()==nums.length){
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            helper(list,nums,results);
            list.remove(list.size()-1);//参数是下标
        }
    }
}
