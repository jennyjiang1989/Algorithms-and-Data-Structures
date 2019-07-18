//Given a collection of candidate numbers (candidates) and a target number (target), 
//find all unique combinations in candidates where the candidate numbers sums to target.
//Each number in candidates may only be used once in the combination.
//Input: candidates = [10,1,2,7,6,1,5], target = 8, (数组本身有重复！)
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results=new ArrayList<>();
        if(candidates==null){
            return results;
        }
        ArrayList<Integer> subset=new ArrayList<>();
        Arrays.sort(candidates);
        helper(subset,candidates,0,target,results);
        return results;
    }
    private void helper(ArrayList<Integer> subset, int[] candidates, int startIndex, int remainTarget, List<List<Integer>> results){
        if(remainTarget==0){
            results.add(new ArrayList<Integer>(subset));
            return;
        }
        for(int i=startIndex;i<candidates.length;i++){
            if(remainTarget<candidates[i]){
                break;
            }
            if(i!=startIndex&&candidates[i]==candidates[i-1]){
                continue;
            }
            subset.add(candidates[i]);
            helper(subset,candidates,i+1,remainTarget-candidates[i],results);
            subset.remove(subset.size()-1);
        }
    }
}
