//Given a triangle, find the minimum path sum from top to bottom. 
//Each step you may move to adjacent numbers on the row below.
public class Triangle {
	public int minimumTotal(int[][] triangle){
		if(triangle==null||triangle.length==0){
			return -1;
		}
		if(triangle[0]==null||triangle[0].length==0){
			return -1;
		}
		//state: f[x][y]=minimum path value from 0,0 to x,y
		int n=triangle.length;
		int[][] f=new int[n][n];
		//initialize
		f[0][0]=triangle[0][0];
		for(int i=1;i<n;i++){
			f[i][0]=f[i-1][0]+triangle[i][0];
			f[i][i]=f[i-1][i-1]+triangle[i][i];
		}
		//top down
		for(int i=1;i<n;i++){
			for(int j=1;j<i;j++){
				f[i][j]=Math.min(f[i-1][j-1],f[i-1][j])+triangle[i][j];
			}
		}
		//answer
		int best=f[n-1][0];
		for(int i=1;i<n;i++){
			best=Math.min(best, f[n-1][i]);
		}
		return best;
	}
}
