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
        //对于每一个物品
        for(int i=1;i<=A.length;i++){
            //对于每一个容量
            for(int j=0;j<=m;j++){
                //不取i物体
                dp[i][j]=dp[i-1][j];
                //取i物体 注意A跟V数组下标从0到A.length-1
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
//Find the number of possible ways to fill the backpack.
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
//idea: 使用0-1背包来计算收到0个offer的最小概率，然后答案就是1-收到0个offer的最小概率

//二维
public class Solution {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public double backpackIX(int n, int[] prices, double[] probability) {
        // write your code here
        //每一所大学拿不到offer的概率
        for(int i=0;i<probability.length;i++){
            probability[i]=1-probability[i];
        }
        //dp[i][j]:用j千元申请前i所学校拿不到一个offer的最小概率
        double[][] dp=new double[prices.length+1][n+1];
        //初始化第一行第一列
        for(int i=0;i<=prices.length;i++){
            dp[i][0]=1.0;
        }
        for(int j=0;j<=n;j++){
            dp[0][j]=1.0;
        }
        for(int i=1;i<=prices.length;i++){
            for(int j=1;j<=n;j++){
                //不申请第i所学校
                dp[i][j]=dp[i-1][j];
                //申请第i所学校
                if(j>=prices[i-1]){
                    dp[i][j]=Math.min(dp[i][j],dp[i-1][j-prices[i-1]]*probability[i-1]);
                }
            }
        }
        return 1-dp[prices.length][n];
    }
}
//一维  逆序枚举
public class Solution {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public double backpackIX(int n, int[] prices, double[] probability) {
        // write your code here
        //每一所大学拿不到offer的概率
        for(int i=0;i<probability.length;i++){
            probability[i]=1-probability[i];
        }
        double[] dp=new double[n+1];
        for(int i=0;i<=n;i++){
            dp[i]=1.0;
        }
        for(int i=0;i<prices.length;i++){
            for(int j=n;j>=prices[i];j--){
                dp[j]=Math.min(dp[j],dp[j-prices[i]]*probability[i]);
            }
        }
        return 1-dp[n];
    }
}
