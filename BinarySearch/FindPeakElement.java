//There is an integer array which has the following features:
//The numbers in adjacent positions are different. A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
//We define a position P is a peak if: A[P] > A[P-1] && A[P] > A[P+1]
//Find a peak element in this array. Return the index of the peak.
public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
       int start=0,end=A.length-1;
       while(start+1<end){
           int mid=start+(end-start)/2;
           if(A[mid]<A[mid-1]){
               end=mid;
           }else if(A[mid]<A[mid+1]){
               start=mid;
           }else{
               return mid;
           }
       }
       return -1;
    }
}
//2D matrix
class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // this is the nlog(n) method
        int low = 1, high = A.length - 2;
        List<Integer> ans = new ArrayList<Integer>();
        while (low <= high) {
            int mid = (low + high) / 2;
            int col = find(mid, A);
            if (A[mid][col] < A[mid - 1][col]) {
                high = mid - 1;
            } else if (A[mid][col] < A[mid + 1][col]) {
                low = mid + 1;
            } else {
                ans.add(mid);
                ans.add(col);
                break;
            }
        }
        return ans;
    }
    //找row这一行的最大值index col
    int find(int row, int[][] A) {
        int col = 0;
        for (int i = 0; i < A[row].length; i++) {
            if (A[row][i] > A[row][col]) {
                col = i;
            }
        }
        return col;
    }
}
