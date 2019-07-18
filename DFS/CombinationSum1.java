//Given a set of candidate numbers (candidates) (without duplicates数组本身没有重复！) and a target number (target), 
//find all unique combinations in candidates where the candidate numbers sums to target.
//The same repeated number may be chosen from candidates unlimited number of times.

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results=new ArrayList<>();
        if(candidates==null){
            return results;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> subset=new ArrayList<>();
        helper(subset,0,candidates,target,results);
        return results;
    }
    //1. 递归的定义 找到所有subset开头的组合，后面部分的和是remainTarget
    private void helper(ArrayList<Integer> subset, int startIndex, int[] candidates, int remainTarget, List<List<Integer>> results){
        //3. 递归的出口
        if(remainTarget==0){
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        //2. 递归的拆解
        for (int i=startIndex; i<candidates.length;i++){
            if(remainTarget<candidates[i]){
                break;
            }
            subset.add(candidates[i]);
            helper(subset,i,candidates,remainTarget-candidates[i],results);//i, not i+1: The same repeated number may be chosen from candidates unlimited number of times
            subset.remove(subset.size()-1);
        }
    }
}
