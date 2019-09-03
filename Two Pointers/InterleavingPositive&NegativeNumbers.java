//Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length <= 2) {
            return;
        }
        Arrays.sort(A);
        int start = 0;
        int end = A.length - 1;
        if (A.length % 2 != 0) {
            if (A[(start + end) / 2] >= 0) {
                end--;
            } else {
                start++;
            }    
        }
        
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start += 2;
            end -= 2;
        }
    }
}
