//Given n kinds of items, and each kind of item has an infinite number available. The i-th item has size A[i] and value V[i].
//Also given a backpack with size m. What is the maximum value you can put into the backpack?
public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    //二维
    public int backPackIII2(int[] A, int[] V, int m) {
        // write your code here
        int n=A.length;
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=m;j++){
                //不取i物品
                dp[i][j]=dp[i-1][j];
                //取i物品
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-A[i-1]]+V[i-1]);
                }
            }
        }
        return dp[n][m];
    }
    //二维优化到一维: 0-1背包一维用逆序；完全背包一维用正序
    public int backPackIII(int[] A, int[] V, int m) {
        // write your code here
        int n=A.length;
        int[] dp=new int[m+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(j>=A[i-1]){
                    dp[j]=Math.max(dp[j],dp[j-A[i-1]]+V[i-1]);
                }
            }
        }
        return dp[m];
    }
}
//You have a total of n yuan. Merchant has three merchandises and their prices are 150 yuan, 250 yuan and 350 yuan. 
//And the number of these merchandises can be considered as infinite. 
//After the purchase of goods need to be the remaining money to the businessman as a tip, finding the minimum tip for the merchant.
public class Solution {
    /**
     * @param n: the money you have
     * @return: the minimum money you have to give
     */
    public int backPackX(int n) {
        // write your code here
        int[] prices={150,250,350};
        int[][] dp=new int[4][n+1];
        for(int i=1;i<=3;i++){
            for(int j=0;j<=n;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=prices[i-1]){
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-prices[i-1]]+prices[i-1]);
                }
            }
        }
        int res=n-dp[3][n];
        return res;
    }
}
//Given an integer array nums[] which contains n unique positive numbers, num[i] indicate the size of ith item. 
//An integer target denotes the size of backpack. 
//Find the number of ways to fill the backpack.
//Each item may be chosen unlimited number of times
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        // write your code here
        int n=nums.length;
        int[][] dp=new int[n+1][target+1];
        dp[0][0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=target;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=nums[i-1]){
                    dp[i][j]+=dp[i][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
}

//Coin Change
//You are given coins of different denominations and a total amount of money amount. 
//Write a function to compute the fewest number of coins that you need to make up that amount. 
//If that amount of money cannot be made up by any combination of the coins, return -1.
public class Solution {
    /**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        // write your code here
        int n=coins.length;
        //dp[i][j]表示使用前i个硬币，总金额为j时需要的最少硬币数量
        int[] dp=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            dp[i]=-1;
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<=amount;j++){
                if(j>=coins[i-1]&&dp[j-coins[i-1]]!=-1){
                    if(dp[j]==-1||dp[j-coins[i-1]]+1<dp[j]){
                        dp[j]=dp[j-coins[i-1]]+1;
                    }
                }
            }
        }
        return dp[amount];
    }
}
//Cutting a Rod
//Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
//Determine the maximum value obtainable by cutting up the rod and selling the pieces.
/*Input: [1, 5, 8, 9, 10, 17, 17, 20] 8 Output: 22
Explanation:
length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
by cutting in two pieces of lengths 2 and 6
*/
//二维
public class Solution {
    /**
     * @param prices: the prices
     * @param n: the length of rod
     * @return: the max value
     */
    public int cutting(int[] prices, int n) {
        // Write your code here
        int[][] dp=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=n;j++){
                //不取i英寸
                dp[i][j]=dp[i-1][j];
                //取i英寸
                if(j>=i){
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-i]+prices[i-1]);
                }
            }
        }
        return dp[n][n];
    }
}
//一维
public class Solution {
    /**
     * @param prices: the prices
     * @param n: the length of rod
     * @return: the max value
     */
    public int cutting(int[] prices, int n) {
        // Write your code here
        //dp[i]表示前i英寸所能获得的最大价值。
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(j>=i){
                    dp[j]=Math.max(dp[j],dp[j-i]+prices[i-1]);
                }
            }
        }
        return dp[n];
    }
}
