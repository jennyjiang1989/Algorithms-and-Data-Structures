//Given a m x n grid filled with non-negative numbers,
//find a path from top left to bottom right which minimizes the sum of all numbers along its path.
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        if(grid==null||grid.length==0){
            return -1;
        }
        if(grid[0]==null||grid[0].length==0){
            return -1;
        }
        int m=grid.length;
        int n=grid[0].length;
        //state f[i][j]:从0,0到i,j的最短路径
        int[][] f=new int[m][n];
        f[0][0]=grid[0][0];
        //最左边一行没左 最上边一行没上
        for(int i=1;i<m;i++){
            f[i][0]=f[i-1][0]+grid[i][0];
        }
        for(int j=1;j<n;j++){
            f[0][j]=f[0][j-1]+grid[0][j];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                f[i][j]=Math.min(f[i][j-1],f[i-1][j])+grid[i][j];
            }
        }
        return f[m-1][n-1];
    }
}
