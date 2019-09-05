//Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.
//两根指针，首先判断正数多还是负数多，并把多的那一部分移到前半部分，最后两根指针分别递增2交换即可

public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        // write your code here
        if(A==null||A.length<3){
            return;
        }
        int countPositive=0;
        int n=A.length;
        for(int i=0;i<n;i++){
            if(A[i]>0){
                countPositive++;
            }
        }
        //1 2 3 -1 -2 -> 1 -2 3 -1 2
        //正数多就把正数放前面
        if(countPositive>n/2){
            int index=0;
            for(int i=0;i<n;i++){
                if(A[i]>0){
                    swap(A,index,i);
                    index++;
                }
            }
            int pos=0;
            int neg=1;
            interleave(A,pos,neg);
        //负数多就把负数放前面
        }else{
            int index=0;
            for(int i=0;i<n;i++){
                if(A[i]<0){
                    swap(A,index,i);
                    index++;
                }
            }
            int neg=0;
            int pos=1;
            interleave(A,pos,neg);
        }
    }
    private void swap(int[] A, int i, int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
    private void interleave(int[] A, int pos, int neg){
        int n=A.length;
        while(pos<n&&neg<n){
            while(pos<n&&A[pos]>0){
                pos+=2;
            }
            while(neg<n&&A[neg]<0){
                neg+=2;
            }
            if(pos>=n||neg>=n){
                break;
            }
            swap(A,pos,neg);
        }
    }
}
