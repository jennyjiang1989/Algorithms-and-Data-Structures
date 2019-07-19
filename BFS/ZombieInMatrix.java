//Given a 2D grid, each cell is either a wall 2, a zombie 1 or people 0. 
//Zombies can turn the nearest people into zombies every day, but can't through wall.
//How long will it take to turn all people into zombies? Return -1 if can not turn all people into zombies.

class Coordinate{
    int x,y;
    public Coordinate(int x,int y){
      this.x=x;
      this.y=y;
    }
}
public class Solution{
  public int PEOPLE=0;
  public int ZOMBIE=1;
  public int WALL=2;
  
  public int[] deltaX=[0,0,-1,1];
  public int[] deltaY=[-1,1,0,0];
  
  public int zombie(int[][] grid){
    if(grid==null||grid.length==0||grid[0].length==0){
      return 0;
    }
    int n=grid.length;
    int m=grid[0].length;
    
    //initialize the queue & count people
    int people=0;
    Queue<Coordinate> queue=new LinkedList<>();
    for(int i=0;i<n;i++){
      for(int j=0;j<m;j++){
        if(grid[i][j]==PEOPLE){
          people++;
        }else if(grid[i][j]==ZOMBIE){
          queue.offer(new Coordinate(i,j));
        }
      }
    }
    if(people==0){
      return 0;
    }
    //bfs
    int days=0;
    while(!queue.isEmpty()){
      days++;
      int size=queue.size();
      for(int i=0;i<size;i++){
        Coordinate zb=queue.poll();
        for(int i=0;i<4;i++){
          Coordinate adj=new Coordinate(zb.x+deltaX[i],zb.y+deltaY[i]);
          if(!isPeople(adj,grid)){
            continue;
          }
          grid[adj.x][adj.y]=ZOMBIE;
          people--;
          if(people==0){
            return days;
          }
          queue.offer(adj);
        }
      }
    }
    return -1;
  }
  private boolean isPeople(Coordinate coor,int[][] grid){
		int n=grid.length;
		int m=grid[0].length;
		
		if(coor.x<0||coor.x>=n){
			return false;
		}
		if(coor.y<0||coor.y>=m){
			return false;
		}
		return (grid[coor.x][coor.y]==PEOPLE);
	}
}
