//Find the kth smallest number in a row and column sorted matrix.
//Each row and each column of the matrix is incremental.
/*Input:
[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
k = 4
Output: 5
*/
//定义一个小根堆, 起始仅仅放入第一行第一列的元素.循环,每一次取出一个元素, 然后把该元素右方以及下方的元素放入堆中, 第k次取出的元素即为答案.
//其中, 要注意一个元素不能重复入堆, 需要记录.

class Element{
    public int x, y, value;
    public Element(int x, int y, int value){
        this.x=x;
        this.y=y;
        this.value=value;
    }
}
public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    Comparator<Element> comparator=new Comparator<Element>(){
        public int compare(Element elem1,Element elem2){
            return elem1.value-elem2.value;
        }
    };
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        PriorityQueue<Element> pq=new PriorityQueue<>(k,comparator);
        int n=matrix.length;
        int m=matrix[0].length;
        pq.add(new Element(0,0,matrix[0][0]));//放入起始元素
        
        boolean[][] hash = new boolean[n][m];//标记是否放入过最小堆
        int[] dx=new int[]{0,1};//右
        int[] dy=new int[]{1,0};//下
        //循环k-1次
        for(int i=0;i<k-1;i++){
            Element cur=pq.poll();//每次取最小
            //把右边和下边元素放进去
            for(int j=0;j<2;j++){
                int next_x = cur.x + dx[j];
                int next_y = cur.y + dy[j];
                //横纵坐标都没有越界并且从来没有放进去过堆
                if(next_x<n && next_y<m && !hash[next_x][next_y]){
                    hash[next_x][next_y] = true;//进行标记
                    Element nextElem=new Element(next_x,next_y,matrix[next_x][next_y]);
                    pq.add(nextElem);
                }
            }
        }
        return pq.peek().value;//取kth最小
    }
}
