//You are climbing a stair case. It takes n steps to reach to the top.
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
class Solution {
    public int climbStairs(int n) {
        int[] f=new int[n+1];
        f[0]=f[1]=1;
        for(int i=2;i<=n;i++){
            f[i]=f[i-2]+f[i-1];
        }
        return f[n];
    }
}
