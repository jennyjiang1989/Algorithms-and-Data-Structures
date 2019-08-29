//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//Given an integer n, return all distinct solutions to the n-queens puzzle.
//Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

public class Solution {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> results=new ArrayList<>();
        if(n<=0){
            return results;
        }
        helper(results,new ArrayList<Integer>(),n);
        return results;
    }
    private void helper(List<List<String>> results, List<Integer> cols, int n){
        if(cols.size()==n){
            results.add(drawChessBoard(cols));
            return;
        }
        for(int i=0;i<n;i++){
            if(!isValid(cols,i)){
                continue;
            }
            cols.add(i);
            helper(results,cols,n);
            cols.remove(cols.size()-1);
        }
    }
    //(2,4,1,3)=>index(1,3,0,2)
    //第一个皇后放在第2个位置；第二个放在第4个位置；第三个放在第1个位置；第四个放在第3个位置
    private List<String> drawChessBoard(List<Integer> cols){
        List<String> chessboard=new ArrayList<>();
        for(int i=0;i<cols.size();i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<cols.size();j++){
                sb.append(j==cols.get(i)?'Q':'.');
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }
    //判断在第column列上放一个新皇后是否可行，根据已有的棋盘cols
    private boolean isValid(List<Integer> cols, int column){
        int row=cols.size();//这也是第row+1行（index为row）要在第column上放一个新皇后
        for(int rowIndex=0;rowIndex<row;rowIndex++){
            //不能在同一列
            if(column==cols.get(rowIndex)){
                return false;
            }
            if(rowIndex+cols.get(rowIndex)==row+column){
                return false;
            }
            if(rowIndex-cols.get(rowIndex)==row-column){
                return false;
            }
        }
        return true;
    }
}
