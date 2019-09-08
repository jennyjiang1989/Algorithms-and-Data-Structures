//For a given source string and a target string, you should output the first index(from 0) of target string in source string.
//If target does not exist in source, just return -1.
public class Solution {
    /**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // Write your code here
        if(source==null||target==null){
            return -1;
        }
        for(int i=0;i<=source.length()-target.length();i++){
            int j=0;
            for(j=0;j<target.length();j++){
                if(source.charAt(i+j)!=target.charAt(j)){
                    break;
                }
            }
            if(j==target.length()){
                return i;
            }
        }
        return -1;
    }
}
