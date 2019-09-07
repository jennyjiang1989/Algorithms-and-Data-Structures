//0-1背包
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
        for(int i=1;i<=A.length;i++){
            for(int j=0;j<=m;j++){
                //不取i物体
                dp[i][j]=dp[i-1][j];
                //取i物体
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-A[i-1]]+V[i-1]);
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
//Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. 
//Find the number of possible fill the backpack.
//Each item may only be used once
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        // write your code here
        //填满j的空间的方案数
        int n=nums.length;
        int[][] dp=new int[n+1][target+1];
        dp[0][0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<=target;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=nums[i-1]){
                    dp[i][j]+=dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
}
//你总共有n 万元，希望申请国外的大学，要申请的话需要交一定的申请费用，给出每个大学的申请费用以及你得到这个大学offer的成功概率，大学的数量是 m。
//如果经济条件允许，你可以申请多所大学。找到获得至少一份工作的最高可能性。
//计算一个offer都收不到的概率，然后减掉。使用0-1背包来计算收到0个offer的最小概率。

//完全背包
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
    //二维优化到一维
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
            for(int j=0;j<i;j++){
                dp[i]=Math.max(dp[i],dp[j]+prices[i-j-1]);
            }
        }
        return dp[n];
    }
}
