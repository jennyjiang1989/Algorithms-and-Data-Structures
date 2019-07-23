//Given two arrays, write a function to compute their intersection.
//Each element in the result must be unique. The result can be in any order.

//Version 1: HashSet 
//time o(n+m) 
//space o(min(n,m))

//Version 2: Quick sort + Merge 
//time o(nlogn+mlogm+n+m)=o(nlogn+mlogm) 
//space o(1)

//Version 3: Binary Search 
//time o(nlogn+mlogn)=o((n+m)logn) 
//space o(1)

//HashSet
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            set.add(nums1[i]);
        }
        ArrayList<Integer> intersection=new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            if(set.contains(nums2[i])&&!intersection.contains(nums2[i])){
                intersection.add(nums2[i]);
            }
        }
        int size=intersection.size();
        int[] result=new int[size];
        for(int i=0;i<size;i++){
            result[i]=intersection.get(i);
        }
        return result;
    }
}
//Sort + 2 pointers
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);//先排序
        Arrays.sort(nums2);
        int i=0,j=0;
        ArrayList<Integer> intersection=new ArrayList<>();
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]==nums2[j]&&!intersection.contains(nums1[i])){
                intersection.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i]<nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        int size=intersection.size();
        int[] result=new int[size];
        for(int k=0;k<size;k++){
            result[k]=intersection.get(k);
        }
        return result;
    }
}
