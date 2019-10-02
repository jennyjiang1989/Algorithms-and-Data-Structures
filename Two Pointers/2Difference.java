//Given an array of integers, find two numbers that their difference equals to a target value.
//where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are NOT zero-based.

//HashMap
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //another-nums[i]=target;
            int sum = nums[i] + target;
            if (map.containsKey(sum)) {
                int index = map.get(sum);
                int[] pair = new int[2];
                pair[0] = index + 1;
                pair[1] = i + 1;
                return pair;
            }
            //nums[i]-another=target
            int diff = nums[i] - target;
            if (map.containsKey(diff)) {
                int index = map.get(diff);
                int[] pair = new int[2];
                pair[0] = index + 1;
                pair[1] = i + 1;
                return pair;
            }
            map.put(nums[i], i);
        }
        
        return null;
    }
}

//2 pointers
class Pair {
    public int idx, num;
    public Pair(int i, int n) {
        this.idx = i;
        this.num = n;
    }
}

public class Solution {
    /*
     * @param nums an array of Integer
     * @param target an integer
     * @return [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        // write your code here
        int[] indexs = new int[2];
        if (nums == null || nums.length < 2)
            return indexs;

        if (target < 0)
            target = -target;

        int n = nums.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; ++i)
            pairs[i] = new Pair(i, nums[i]);

        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                return p1.num - p2.num;
            } 
        });

        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (i == j)
                j ++;
            while (j < n && pairs[j].num - pairs[i].num < target)
                j ++;

            if (j < n && pairs[j].num - pairs[i].num == target) {
                indexs[0] = pairs[i].idx + 1;
                indexs[1] = pairs[j].idx + 1;
                if (indexs[0] > indexs[1]) {
                    int temp = indexs[0];
                    indexs[0] = indexs[1];
                    indexs[1] = temp;
                }
                return indexs;
            }
        }
        return indexs;
    }
}

//Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.
import java.util.*;
public class countPairs {
	public static void main(String[] args){
		int[] A={1,5,4,1,2,7};
		int k=3;
		int count1=count1(A,k);
		System.out.println(count1);
		int count2=count2(A,k);
		System.out.println(count2);
	}
	//Time Complexity: O(n)
    public static int count1(int[] A, int k){
    	int count=0;
    	HashSet<Integer> set=new HashSet<>();//1,2,4,5,7
    	for(int i=0;i<A.length;i++){
    		set.add(A[i]);
    	}
    	for(int i=0;i<A.length;i++){
    		int smaller=A[i]-k;
    		if(set.contains(smaller)){
    			count++;
    		}
    		int bigger=A[i]+k;
    		if(set.contains(bigger)){
    			count++;
    		}
    		set.remove(A[i]);
    	}
    	return count;
    }
    //Time Complexity: O(nlogn)
    public static int count2(int[] A, int k){
    	int count=0;
    	Arrays.sort(A);// 1 1 2 4 5
    	int i=0,j=0;
    	while(i<=j && j<A.length){
    		if(A[j]-A[i]==k){
    			count++;
    			i++;
    			j++;
    		}else if(A[j]-A[i]<k){
    			j++;
    		}else{
    			i++;
    		}
    	}
    	return count;
    }
}
