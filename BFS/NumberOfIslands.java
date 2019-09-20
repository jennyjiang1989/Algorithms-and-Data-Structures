//把矩阵理解成图：只有上下左右4个邻接点的图
//矩阵R行C列，R*C个点 R*C*2条边
//矩阵中BFS时间复杂度=O(R*C)

//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.

class Solution {
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0){
            return 0;
        }
        int n=grid.length;
        int m=grid[0].length;
        int islands=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    markByBFS(grid,i,j);
                    islands++;//进行了几次BFS,就是几个Island
                }
            }
        }
        return islands;
    }
    class Coordinate{
        int x,y;
        public Coordinate(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    private void markByBFS(char[][] grid,int x,int y){
        int[] deltaX={0,0,-1,1};
        int[] deltaY={-1,1,0,0};
        Queue<Coordinate> queue=new LinkedList<>();
        queue.offer(new Coordinate(x,y));
        grid[x][y]='0';
        while(!queue.isEmpty()){
            Coordinate coor=queue.poll();
            for(int i=0;i<4;i++){
                Coordinate adj=new Coordinate(coor.x+deltaX[i],coor.y+deltaY[i]);
                if(!inBound(adj,grid)){
                    continue;
                }
                if(grid[adj.x][adj.y]=='1'){
                    queue.offer(adj);
                    grid[adj.x][adj.y]='0';
                }
            }
        }
    }
    private boolean inBound(Coordinate coor, char[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        return coor.x>=0 && coor.x<n && coor.y>=0 && coor.y<m;
    }
}

//Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. 
//We only consider up/down/left/right adjacent. Find the number of islands that size bigger or equal than K.

https://www.jiuzhang.com/solution/number-of-big-islands/
