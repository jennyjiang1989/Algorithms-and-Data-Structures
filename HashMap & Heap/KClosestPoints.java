//Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
//Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        //k:initial capacity
        PriorityQueue<Point> pq=new PriorityQueue<>(k,new Comparator<Point>(){
            public int compare(Point point1,Point point2){
                int diff=distance(point2, origin)-distance(point1,origin);//最大堆 由大到小排
                if(diff==0){
                    diff=point2.x-point1.x;
                }
                if(diff==0){
                    diff=point2.y-point1.y;
                }
                return diff;
            }
        });
        for(int i=0;i<points.length;i++){
            pq.offer(points[i]);
            if(pq.size()>k){
                pq.poll();
            }
        }
        
        Point[] result=new Point[k];
        while(!pq.isEmpty()){
            result[k-1]=pq.poll();
            k--;
        }
        return result;
    }
    private int distance(Point point1, Point point2){
        return (point1.x-point2.x)*(point1.x-point2.x)+(point1.y-point2.y)*(point1.y-point2.y);
    }
}
