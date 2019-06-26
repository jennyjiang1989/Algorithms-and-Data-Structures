import java.util.*;
public class TopKLargestNumbers {
	public int[] topk(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            if(pq.size()<k){
                pq.add(nums[i]);
            }else{
                if(pq.peek()<nums[i]){
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }
        int[] ans=new int[k];
        for(int i=k-1;i>=0;i--){
            ans[i]=pq.poll();
        }
        return ans;
        //if return kth largest element, just return pq.poll();
    }
}
