public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // write your code here
        PriorityQueue<Integer> pq=new PriorityQueue<>(k);
        int[] topK=new int[k];
        for(int i=0;i<nums.length;i++){
            if(pq.size()<k){
                pq.add(nums[i]);
            }else{
                pq.poll();
                pq.add(nums[i]);
            }
        }
        for(int i=0;i<k;i++){
            topK[k-i-1]=pq.poll();
        }
        return topK;
    }
}

