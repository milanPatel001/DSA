import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
    static int max =-1;
    public static void main(String[] args) {
        int[][] arr = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };

        nQueens(arr,0,0);
    }

    static List<String> wordBreak(int n, List<String> dict, String s)
    {
        List<String> ans = new ArrayList<>();
        wordBreak(n,dict,s,ans,"");
        return ans;
    }

    static void wordBreak(int n, List<String> dict, String s, List<String> ans, String currentWord)
    {
        /*
                    "","catsanddogs"
                     /              \                                             dict={cat,cats,and,sand,dogs}
             cat,sanddogs      cats,anddogs                                   //branch when word is found
                   |                 |
            cat sand,dogs       cats and,dogs
                   |                 |
            cat sand dogs,""    cats and dogs,""   //print when s.length()==0
         */


        if(s.length()==0){
            ans.add(currentWord.trim());
            return;
        }

        for(int i=0;i<s.length();i++){
            String part = s.substring(0,i+1);
            if(dict.contains(part)){
                wordBreak(n,dict,s.substring(i+1),ans,currentWord+ part+ " ");
            }
        }
    }


    public static boolean equalPartition(int N, int[] arr,int i,int yesSum,int noSum){
        if(i==N){
            if(yesSum==noSum){
                return true;
            }
            return false;
        }

        return equalPartition(N,arr,i+1,yesSum+arr[i],noSum) || equalPartition(N,arr,i+1,yesSum,noSum+arr[i]);
    }

    public static int longestRoute(int[][] arr,int row, int col,int[][] visited,int count){
        if(row==1 && col==7){
            max = Math.max(max,count);
            return max;
        }

        if(row<0 || col<0 || row== arr.length || col== arr[0].length
                || visited[row][col]==1 || arr[row][col]==0) return max;

        visited[row][col] = 1;
        max = longestRoute(arr,row+1,col,visited,count+1);
        max = longestRoute(arr,row,col+1,visited,count+1);
        max = longestRoute(arr,row,col-1,visited,count+1);
        max = longestRoute(arr,row-1,col,visited,count+1);

        visited[row][col] = 0;
        return max;
    }


    public static void ratInAMaze(int[][] arr, int i, int j, int[][] visited, ArrayList<String> ans, String path){
        if(arr[arr.length-1][arr.length-1]==0) return;

        if(i >= arr.length || j>= arr.length || i < 0 || j<0 || arr[i][j]==0 || visited[i][j]==1) return;


        if(i== arr.length-1 && j== arr.length-1){
            ans.add(path);
            return;
        }

        visited[i][j]=1;

        ratInAMaze(arr,i+1,j,visited,ans,path+"D");
        ratInAMaze(arr,i,j+1,visited,ans,path+"R");
        ratInAMaze(arr,i-1,j,visited,ans,path+"U");
        ratInAMaze(arr,i,j-1,visited,ans,path+"L");

        visited[i][j]=0;

    }

    public static boolean isQueenSafe(int[][] arr,int row, int col){
        for(int i=0;i< row;i++){
            if(arr[i][col]==1) return false;
        }

        int i = row-1;
        int j = col+1;
        while (i<arr.length && j< arr.length && i>=0 && j>=0){
            if(arr[i][j]==1) return false;
            i--;
            j++;
        }

        i = row-1;
        j = col-1;

        while (i<arr.length && j< arr.length && i>=0 && j>=0){
            if(arr[i][j]==1) return false;
            i--;
            j--;
        }
        return true;
    }

    public static void printBoard(int[][] arr){
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr.length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void nQueens(int[][] arr, int row, int col){
        if(col>= arr.length) return;

        if(row == arr.length){
            printBoard(arr);
            return;
        }

        /* Without using rowIndex
        for(int i=0;i< arr.length;i++){
            if(isQueenSafe(arr,i,col)){
                arr[i][col]=1;
                nQueens(arr,col+1);
                arr[i][col]=0;
            }
        }
        */


        if(isQueenSafe(arr,row,col)){
            arr[row][col]=1;
            nQueens(arr,row+1,0);
            arr[row][col]=0;
        }

        nQueens(arr,row,col+1);
    }
}
