//Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.
//两根指针，首先判断正数多还是负数多，并把多的那一部分移到后半部分，最后两根指针分别递增2交换即可

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public int[] rerange(int[] A) {
        // Check the input parameter.
        if(A == null || A.length < 3)
            return A;
        int n = A.length;
        int countPositive = 0;//count the number of positive numbers
        
        // store the positive numbers index.
        int positiveIndex = 0;
        int pos = 1;
        int neg = 0;
        for(int i=0;i<n;i++) {
                if(A[i] > 0) {
                // Put all the positive numbers at in the left part.
                    swap(A,positiveIndex++,i);
                    countPositive++;
                }
        }
        
        if(countPositive > n/2) {
        // If positive numbers are more than negative numbers,
        // Put the positive numbers at first.
            pos = 0;
            neg = 1;
            // Reverse the array.
            
            int left = 0;
            int right = n-1;
            while(left < right) {
                swap(A,left,right);
                left++;
                right--;
            }
        }
        
        while(pos < n && neg <n) {
            while(pos<n && A[pos]>0)
                pos +=2;
            while(neg<n && A[neg]<0)
                neg +=2;
            if(neg >= n || pos>=n)
                break;
            swap(A,pos,neg);
        }
       return A; 
   }
   
   public void swap(int[] A, int l, int r) {
       int temp = A[l];
       A[l] = A[r];
       A[r] = temp;
   }
}
