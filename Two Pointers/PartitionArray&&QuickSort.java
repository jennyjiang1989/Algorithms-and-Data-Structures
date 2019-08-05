//Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
//All elements < k are moved to the left; All elements >= k are moved to the right
//Return the partitioning index, i.e the first index i nums[i] >= k.
public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if(nums==null||nums.length==0){
            return 0;
        }
        int i=0,j=nums.length-1;
        while(i<=j){
            while(i<=j && nums[i]<k){
                i++;
            }
            while(i<=j && nums[j]>=k){
                j--;
            }
            if(i<=j){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                i++;
                j--;
            }
        }
        return i;
    }
}
//Quick Sort O(nlogn) - 递归
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
        quickSort(A,0,A.length-1);
    }
    private void quickSort(int[] A, int start, int end){
        if(start>=end){
            return;
        }
        int i=start,j=end;
        int pivot=A[(start+end)/2];
        while(i<=j){
            while(i<=j && A[i]<pivot){
                i++;
            }
            while(i<=j && A[j]>pivot){
                j--;
            }
            if(i<=j){
                int temp=A[i];
                A[i]=A[j];
                A[j]=temp;
                i++;
                j--;
            }
        }
        //start j i end
        quickSort(A,start,j);
        quickSort(A,i,end);
    }
}
