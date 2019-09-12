//假设你身上有 n 元，超市里有多种大米可以选择，每种大米都是袋装的，必须整袋购买，给出每种大米的重量，价格以及数量，求最多能买多少公斤的大米
//输入:  n = 8, prices = [3,2], weights = [300,160], amounts = [1,6]
//输出:  640	
//解释:  全买价格为2的米。
public class Solution {
    /**
     * @param n: the money of you
     * @param prices: the price of rice[i]
     * @param weight: the weight of rice[i]
     * @param amounts: the amount of rice[i]
     * @return: the maximum weight
     */
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        // write your code here
        int m = prices.length;//多少种物品
        int[] f = new int[n + 1];
        //对于每一种物品
        for(int i = 0;i < m;i++){
            //对于每一个物品
            for(int j = 1;j <= amounts[i];j++){
                //逆序 0-1背包 
                for(int k = n;k >= prices[i];k--){
                    f[k] = Math.max(f[k], f[k - prices[i]] + weight[i]);
                }
            }
        }
        return f[n];
    }
}

//给一些不同价值和数量的硬币。找出这些硬币可以组合在1 ~ n范围内的值的数量
//Input:  n = 5 value = [1,4] amount = [2,1] Output:  4
//Explanation: They can combine 4 numbers which are 1,2,4,5.
//在背包的过程中，当前考虑第i种面值，要记录组成j的时候使用了几个i，如果用超出数量个就不更新。
public class Solution {
    /**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */
    public int backPackVIII(int n, int[] value, int[] amount) {
        // write your code here
        int m = value.length;//物品种数
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;//0是肯定可以的
        int res = 0;
        //对于每一种物品
        for(int i = 0;i < m;i++){
            //标记组成j值时用了几个i
            int[] cnt = new int[n + 1];
            for(int j = value[i];j <= n;j++){
                //1.可以组成j - value[i]面值；2.当前j面值还没取到过; 3.还没用光i物品
                if(dp[j - value[i]] && !dp[j] && cnt[j - value[i]] < amount[i]){
                    cnt[j] += cnt[j - value[i]] + 1;//标记
                    res++;
                    dp[j] = true;
                }
            }
        }
        return res;
    }
}
