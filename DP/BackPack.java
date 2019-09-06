//There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.
//What's the maximum value can you put into the backpack?
//Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4] Output: 9
//Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9 
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
        //dp[i][j]表示前i个物品，在容量为j的情况下，能取到的最大价值
        int[][] dp=new int[A.length+1][m+1];
        for(int i=0;i<=A.length;i++){
            for(int j=0;j<=m;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                //已经超过容量j
                }else if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    //不取第i个物体 与 取第i个物体 的最大值
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-A[i-1]]+V[i-1]);
                }
            }
        }
        return dp[A.length][m];
    }
}
//Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        //能不能用前i个物品装下当前j的空间 0不能 1能
        boolean dp[][]=new boolean[A.length+1][m+1];
        //初始化
        for(int i=0;i<=A.length;i++){
            for(int j=0;j<=m;j++){
                dp[i][j]=false;
            }
        }
        dp[0][0]=true;
        
        for(int i=1;i<=A.length;i++){
            for(int j=0;j<=m;j++){
                //不装第i个物品
                dp[i][j]=dp[i-1][j];
                //装第i个物品
                if(j>=A[i-1]&&dp[i-1][j-A[i-1]]){
                    dp[i][j]=true;
                }
            }
        }
        for(int i=m;i>=0;i--){
            if(dp[A.length][i]){
                return i;
            }
        }
        return 0;
    }
}

