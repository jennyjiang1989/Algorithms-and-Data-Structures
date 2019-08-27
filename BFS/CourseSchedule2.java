class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];//邻接表
        int[] degree = new int[numCourses];//入度
        Queue<Integer> queue = new LinkedList<>();//bfs
        ArrayList<Integer> order=new ArrayList<>();//因为不知道是否有拓扑排序（能否完成所有的课程）
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList<Integer>();
        //构造邻接表与统计入度同时进行   
        for(int i=0; i<prerequisites.length;i++){
            int u=prerequisites[i][0];
            int v=prerequisites[i][1];
            degree[u]++;
            graph[v].add(u);
        }
        //把入度为0的点加入queue
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
            }
        }
        //bfs
        while(!queue.isEmpty()){
            int course = queue.poll();
            order.add(course);
            int size=graph[course].size();
            for(int i=0; i<size;i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                }
            }
        }
        if(order.size()==numCourses){
            int[] result=new int[numCourses];
            for(int i=0;i<numCourses;i++){
                result[i]=order.get(i);
            }
            return result;
        }else{
            return new int[0];//empty array with 0 element
        }
    }
}
