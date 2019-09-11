//Given k sorted integer arrays, merge them into one sorted array.

class Element{
        public int row,col,val;
        Element(int row, int col, int val){
            this.row=row;
            this.col=col;
            this.val=val;
        }
    }
public class Solution {
    private Comparator<Element> comparator=new Comparator<Element>(){
        public int compare(Element e1,Element e2){
            return e1.val-e2.val;
        }
    };
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        if(arrays==null||arrays.length==0){
            return new int[0];
        }
        PriorityQueue<Element> pq=new PriorityQueue<>(arrays.length,comparator);
        int total=0;
        for(int i=0;i<arrays.length;i++){
            if(arrays[i].length>0){
                Element elem=new Element(i,0,arrays[i][0]);
                pq.add(elem);
                total+=arrays[i].length;
            }
        }
        int[] ans=new int[total];
        int index=0;
        while(!pq.isEmpty()){
            Element elem=pq.poll();
            ans[index++]=elem.val;
            if(elem.col+1<arrays[elem.row].length){
                Element nextEle=new Element(elem.row,elem.col+1,arrays[elem.row][elem.col+1]);
                pq.add(nextEle);
            }
        }
        return ans;
    }
}
