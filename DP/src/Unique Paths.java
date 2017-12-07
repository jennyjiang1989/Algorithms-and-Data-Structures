//A robot is located at the top-left corner of a m x n grid.
//The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
//How many possible unique paths are there?

public class Solution {
    /*
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if(m==0||n==0){
            return 1;
        }
        int[][] f=new int[m][n];
        for(int i=0;i<m;i++){
            f[i][0]=1;
        }
        for(int j=1;j<n;j++){
            f[0][j]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                f[i][j]=f[i][j-1]+f[i-1][j];
            }
        }
        return f[m-1][n-1];
    }
}
