//o(n)
public class Solution {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        if(A==null||B==null){
            return null;
        }
        int lengthA=A.length;
        int lengthB=B.length;
        int lengthC=lengthA+lengthB;
        int[] C=new int[lengthC];
        int i=0,j=0,k=0;
        while(i<lengthA&&j<lengthB){
            if(A[i]<B[j]){
                C[k++]=A[i++];
            }else{
                C[k++]=B[j++];
            }
        }
        while(i<lengthA){
            C[k++]=A[i++];
        }
         while(j<lengthB){
            C[k++]=B[j++];
        }
        return C;
    }
}
//将小数组归并到大数组里：倒过来合并
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1;
        int j=n-1;
        int k=m+n-1;
        while(i>=0&&j>=0){
            if(nums1[i]>nums2[j]){
                nums1[k--]=nums1[i--];
            }else{
                nums1[k--]=nums2[j--];
            }
        }
        while(j>=0){
            nums1[k--]=nums2[j--];
        }
    }
}
