//Given a string s, partition s such that every substring of the partition is a palindrome.
//Return all possible palindrome partitioning of s.
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
"abc" - "a1b2c"
a b c -> [1,2]
a bc -> [1]
ab c -> [2]
abc -> []
n个字母的字符串 -> n-1个数字的组合

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> results=new ArrayList<>();
        if(s==null||s.length()==0){
            return results;
        }
        ArrayList<String> partition=new ArrayList<>();
        helper(partition,0,s,results);
        return results;
    }
    private void helper(ArrayList<String> partition,
                        int startIndex,
                        String s,
                        List<List<String>> results){
        if(startIndex==s.length()){
            results.add(new ArrayList<String>(partition));
            return;
        }
        for(int i=startIndex;i<s.length();i++){
            String subString=s.substring(startIndex,i+1);
            if(!isPalindrome(subString)){
                continue;
            }
            partition.add(subString);
            helper(partition,i+1,s,results);
            partition.remove(partition.size()-1);
        }
    }
    private boolean isPalindrome(String s){
        for(int i=0,j=s.length()-1;i<j;i++,j--){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
