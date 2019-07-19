//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        if(nums==null){
            return results;
        }
        Arrays.sort(nums);//sort is necessary
        ArrayList<Integer> list=new ArrayList<>();
        int[] visited=new int[nums.length];//是否被访问过：0（NO）; 1(YES)
        helper(list,nums,visited,results);
        return results;
    }
    private void helper(ArrayList<Integer> list,
                        int[] nums,
                        int[] visited, 
                        List<List<Integer>> results){
        if(list.size()==nums.length){
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(visited[i]==1){
                continue;
            }
            //选代表
            if(i!=0 && nums[i]==nums[i-1] && visited[i-1]==0){
                continue;
            }
            list.add(nums[i]);
            visited[i]=1;
            helper(list,nums,visited,results);
            list.remove(list.size()-1);
            visited[i]=0;
        }
    }
}
