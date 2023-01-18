import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class multidimArray {
    public static void main(String[] args) {
        int[][] matrix = {{6, 6, 2},
                        {5, 6, 7},
                     {9, 10, 11}};

        spiralTraversal(matrix,3 ,3);
    }


    public static int celebrityProblem(int[][] arr){
        int start = 0;
        int end = arr.length-1;

        while(start<end){
            if(arr[start][end]==1) start++;
            else end--;
        }

        for(int i=0;i< arr.length;i++){
            if(i!=start)
                if(arr[i][start]==1 && arr[start][i]==0) return -1;
        }
        return start;
    }

    public static int[][] mergeIntervals(int[][] arr){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr, Comparator.comparingInt(i -> i[0]));

        int first = arr[0][0];
        int last = arr[0][1];
        ArrayList<Integer> temp;

        for(int i=1;i< arr.length;i++){
            temp = new ArrayList<>();
            if(arr[i][0]>last){
                temp.add(first);
                temp.add(last);

                ans.add(temp);

                first = arr[i][0];
                last = arr[i][1];

            }else{
                last = Math.max(last,arr[i][1]);
            }

        }

        //adding last interval
        temp = new ArrayList<>();

        temp.add(first);
        temp.add(last);

        ans.add(temp);

        return ans.stream().map(u  ->  u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
    }


    int rowWithMax1s(int arr[][], int n, int m) {
        /* 1. Optimized brute force
        int maxCount = 0;
        int index = -1;
        for(int i=0;i<n;i++){
            Arrays.sort(arr[i]);
            int count = 0;
            for(int j=m-1;j>=0;j--){
                if(arr[i][j]==0) break;
                if(arr[i][j]==1){
                    count++;
                    if(count==m) return i;
                    if(maxCount<count){
                        maxCount = count;
                        index = i;
                    }
                }
            }

        }
        return index;
    }
         */
    //2. Using two pointers starting from top right corner
        int i = 0;
        int j = m-1;
        int index = -1;

        while(i<n && j>=0){
            if(arr[i][j]==1){
                j--;
                index = i;
            }
            else{
                i++;
            }
        }
    return index;
    }

    public static void spiralTraversal(int[][] arr,int r, int c) {

        int currentRow = 0;
        int maxRow = r - 1;

        int currentCol = 0;
        int maxCol = c - 1;

        int count = 0;
        while (currentRow<=maxRow && count<r*c) {

            for (int i = currentCol; i < maxCol && count<(r*c); i++) {
                System.out.print(arr[currentRow][i]+" ");
                count++;
            }

            for (int j = currentRow; j < maxRow && count<(r*c); j++) {
                System.out.print(arr[j][maxCol]+" ");
                count++;
            }

            for (int i = maxCol; i > currentCol && count<(r*c); i--) {
                System.out.print(arr[maxRow][i]+" ");
                count++;
            }

            currentRow += 1;

            for (int j = maxRow; j >= currentRow && count<(r*c); j--) {
                System.out.print(arr[j][currentCol]+" ");
                count++;
            }

            maxCol-=1;
            maxRow = maxCol;
            currentCol+=1;
        }
    }
}
