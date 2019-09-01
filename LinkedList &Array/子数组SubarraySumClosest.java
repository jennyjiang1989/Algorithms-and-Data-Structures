//Find a subarray with sum closest to zero. Return the indexes of the first number and last number.
//前缀和数组构造了出来 subArray=prefixSum2-prefixSum1最接近0==>prefixSum2最接近prefixSum1==>排序找差值最小

class Pair{
    int sum;
    int index;
    public Pair(int sum,int index){
        this.sum=sum;
        this.index=index;
    }
}

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] result=new int[2];
        if(nums==null||nums.length==0){
            return result;
        }
        if(nums.length==1){
            result[0]=result[1]=0;
            return result;
        }
        //构建前缀和数组
        Pair[] prefixSum=new Pair[nums.length+1];
        prefixSum[0]=new Pair(0,0);
        for(int i=1;i<=nums.length;i++){
            prefixSum[i]=new Pair(prefixSum[i-1].sum+nums[i-1],i);
        }
        //前缀和数组排序
        Arrays.sort(prefixSum,new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.sum-b.sum;
            }
        });//do not forget ;
        int closest=Integer.MAX_VALUE;
        for(int i=1;i<=nums.length;i++){
            if(closest>prefixSum[i].sum-prefixSum[i-1].sum){
                closest=prefixSum[i].sum-prefixSum[i-1].sum;
				if(prefixSum[i].index<=prefixSum[i-1].index){
				    result[0]=prefixSum[i].index;
				    result[1]=prefixSum[i-1].index-1;
				}else{
				    result[0]=prefixSum[i-1].index;
				    result[1]=prefixSum[i].index-1;
				}
            }
        }
        return result;
    }
}
