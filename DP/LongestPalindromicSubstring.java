//Given a string S, find the longest palindromic substring in S. 
//You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
//DP
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        if(s==null||s.length()==0){
            return "";
        }
        int n=s.length();
        //table[i][j] will be true if substring str[i..j] is palindrome, else false
        boolean[][] table=new boolean[n][n];
        int longest=1;
        //All substrings of length 1 are palindromes
        for(int i=0;i<n;i++){
            table[i][i]=true;
        }
        //check for sub-string of length 2
        int start=0;
        //注意index
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                table[i][i+1]=true;
                start=i;
                longest=2;
            }
        }
        //check for sub-string of length 3 and more
        //k: the length of substring
        for(int k=3;k<=n;k++){
            //Fix the starting index 举例子
            for(int i=0;i<=n-k;i++){
                //calculate the endling index of substring from starting index i and length k
                int j=i+k-1;
                if(table[i+1][j-1]&&s.charAt(i)==s.charAt(j)){
                    table[i][j]=true;
                    if(k>longest){
                        longest=k;
                        start=i;
                    }
                }
            }
        }
        return s.substring(start,start+longest);
    }
}
//基于中心点枚举的算法，时间复杂度 O(n^2)
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int start = 0, len = 0, longest = 0;
        for (int i = 0; i < s.length(); i++) {
            len = findLongestPalindromeFrom(s, i, i);
            if (len > longest) {
                longest = len;
                start = i - len / 2;
            }
            
            len = findLongestPalindromeFrom(s, i, i + 1);
            if (len > longest) {
                longest = len;
                start = i - len / 2 + 1;
            }
        }
        
        return s.substring(start, start + longest);
    }
    
    private int findLongestPalindromeFrom(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            len += left == right ? 1 : 2;
            left--;
            right++;
        }
        
        return len;
    }
}
