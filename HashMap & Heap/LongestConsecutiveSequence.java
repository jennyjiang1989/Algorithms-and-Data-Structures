//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//Input : [100, 4, 200, 1, 3, 2] Output : 4 Explanation : The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length:4
//o(n)
public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] num) {
        // write your code here
        if(num==null||num.length==0){
            return 0;
        }
        HashSet<Integer> set=new HashSet<>();
        for(int item:num){
            set.add(item);
        }
        int ans=0;
        for(int item:num){
            if(set.contains(item)){
                set.remove(item);
                int left=item-1;
                int right=item+1;
                while(set.contains(left)){
                    set.remove(left);
                    left--;
                }
                while(set.contains(right)){
                    set.remove(right);
                    right++;
                }
                ans=Math.max(ans,right-left-1);//举例即可得到
            }
        }
        return ans;
    }
}
