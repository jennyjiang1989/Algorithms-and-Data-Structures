//Given an array of integers, how many three numbers can be found in the array, 
//so that we can build an triangle whose three edges length is the three numbers that we find?

public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        // write your code here
        if(S==null||S.length<3){
            return 0;
        }
        Arrays.sort(S);
        int count=0;
        for(int i=2;i<S.length;i++){
            int start=0;
            int end=i-1;
            while(start<end){
                if(S[start]+S[end]>S[i]){
                    count=count+end-start;
                    end--;
                }else{
                    start++;
                }
            }
        }
        return count;
    }
}
