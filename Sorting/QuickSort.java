public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        // write your code here
        if(A==null||A.length==0){
            return;
        }
        int start=0;
        int end=A.length-1;
        quickSort(A,start,end);
    }
    private void quickSort(int[] A, int start, int end){
        if(start>=end){
            return;
        }
        int pivot=A[start+(end-start)/2];
        int left=start;
        int right=end;
        while(left<=right){
            while(left<=right&&A[left]<pivot){
                left++;
            }
            while(left<=right&&A[right]>pivot){
                right--;
            }
            //交换
            if(left<=right){
                int temp=A[left];
                A[left]=A[right];
                A[right]=temp;
                left++;
                right--;
            }
        }
        //start right left end
        quickSort(A,start,right);
        quickSort(A,left,end);
    }
}
