//A robot is located at the top-left corner of a m x n grid.
//The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
//How many possible unique paths are there?
public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if(m==0||n==0){
            return 0;
        }
        int[][] f=new int[m][n];
        for(int i=0;i<m;i++){
            f[i][0]=1;
        }
        for(int j=0;j<n;j++){                                    
            f[0][j]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                f[i][j]=f[i-1][j]+f[i][j-1];
            }
        }
        return f[m-1][n-1];
    }
}

//Now consider if some obstacles are added to the grids. How many unique paths would there be?
public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0]==null||obstacleGrid[0].length==0){
            return 0;
        }
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int[][] f=new int[m][n];
        
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]!=1){
                f[i][0]=1;
            }else{
                break;
            }
        }
        for(int j=0;j<n;j++){
            if(obstacleGrid[0][j]!=1){
                f[0][j]=1;
            }else{
                break;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]!=1){
                    f[i][j]=f[i-1][j]+f[i][j-1];
                }else{
                    f[i][j]=0;
                }
            }
        }
        return f[m-1][n-1];
    }
}
