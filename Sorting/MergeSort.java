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
        // use a shared temp array, the extra memory is O(n) at least
        int[] temp=new int[A.length];
        mergeSort(A,0,A.length-1,temp);
    }
    private void mergeSort(int[] A, int start, int end, int[] temp){
        if(start>=end){
            return;
        }
        int mid=start+(end-start)/2;
        mergeSort(A,start,mid,temp);
        mergeSort(A,mid+1,end,temp);
        merge(A,start,mid,end,temp);
    }
    private void merge(int[] A,int start,int mid, int end, int[] temp){
        int left=start;//左半个数组起始
        int right=mid+1;//右半个数组起始
        int index=start;//temp数组的索引
        // merge two sorted subarrays in A to temp array
        while(left<=mid&&right<=end){
            if(A[left]<A[right]){
                temp[index++]=A[left++];
            }else{
                temp[index++]=A[right++];
            }
        }
        while(left<=mid){
            temp[index++]=A[left++];
        }
        while(right<=end){
            temp[index++]=A[right++];
        }
        //copy temp back to A
        for(int i=start;i<=end;i++){
            A[i]=temp[i];
        }
    }
}
