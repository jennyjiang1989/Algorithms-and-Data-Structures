//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
/* The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
[
[2],
[3,4],
[6,5,7],
[4,1,8,3]
]
*/
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if(triangle==null||triangle.length==0){
            return -1;
        }
        if(triangle[0]==null||triangle[0].length==0){
            return -1;
        }
        //state:f[x][y] = minimum path value from 0,0 to x,y
        int n=triangle.length;
        int[][] f=new int[n][n]; 
        //初始化f[0][0]
        f[0][0]=triangle[0][0];
        //三角形的左边没有左上；三角形的右边没有上
        for(int i=1;i<n;i++){
            f[i][0]=f[i-1][0]+triangle[i][0];
            f[i][i]=f[i-1][i-1]+triangle[i][i];
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<i;j++){
                f[i][j]=Math.min(f[i-1][j],f[i-1][j-1])+triangle[i][j];
            }
        }
        
        int best=f[n-1][0];
        for(int j=1;j<n;j++){
             best = Math.min(best, f[n - 1][j]);
        }
        return best;
    }
}
