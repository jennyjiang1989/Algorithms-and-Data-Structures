//Integers in each row are sorted from left to right. 
//The first integer of each row is greater than the last integer of the previous row.
public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix==null||matrix.length==0){
            return false;
        }
        if(matrix[0]==null||matrix[0].length==0){
            return false;
        }
        int m=matrix.length,n=matrix[0].length;
        int start=0,end=m*n-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(matrix[mid/n][mid%n]==target){
                return true;
            }else if(matrix[mid/n][mid%n]>target){
                end=mid;
            }else{
                start=mid;
            }
        }
        if(matrix[start/n][start%n]==target){
            return true;
        }
        if(matrix[end/n][end%n]==target){
            return true;
        }
        return false;
    }
}

//Return the occurrence of it.
//Integers in each row are sorted from left to right.
//Integers in each column are sorted from up to bottom.
//No duplicate integers in each row or column.
public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix==null||matrix.length==0){
            return 0;
        }
        if(matrix[0]==null||matrix[0].length==0){
            return 0;
        }
        int ans=0;
        // from bottom left to top right
        int r=matrix.length-1;
        int c=0;
        while(r>=0&&c<matrix[0].length){
            if(matrix[r][c]==target){
                ans++;
                r--;
                c++;
                continue;
            }
            if(matrix[r][c]>target){
                r--;
            }else{
                c++;
            }
        }
        return ans;
    }
}
