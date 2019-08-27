class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> order=new ArrayList<>();
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
            
        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
            }
        }
        while(queue.size()!= 0){
            int course = queue.poll();
            order.add(course);
            for(int i=0; i<graph[course].size();i++){
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
