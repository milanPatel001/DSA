import java.util.Arrays;

public class SortingAlgo {
    public static void main(String[] args) {
        int[] a = {2,2};

         quickSort(a,0,a.length-1);

        System.out.println(Arrays.toString(a));
    }

    public static void quickSort(int[] arr,int low,int high){
        if(low>=high) return;

        int pivot = partition(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);

    }

    public static int partition(int[] arr,int low, int high){
        //selecting pivot as last element
        int pivot = arr[high];

        //using 2 ptrs to place pivot at its correct position where left of pivot will have smaller elements and right of pivot will have larger elements.
        int i=low;
        for(int j=low;j<=high;j++){
            if(arr[j]<pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,high);
        return i;
    }



    public static void mergeSort(int[] arr,int start,int end){

        if(start<end) {
            int mid = (start + end) / 2;

            mergeSort(arr, 0, mid);
            mergeSort(arr, mid + 1, end);

            merge(arr,start,mid,end);
        }
    }

    public static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static void insertionSort(int[] arr){
        for (int i=1;i< arr.length;i++){
            int curr = arr[i];
            int j= i-1;

            //shifting elements to right
            while(j>=0 && curr<arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            //placing key at appropriate position
            arr[j+1] = curr;
        }
    }

    public static void selectionSort(int[] arr){
        for(int i=0;i< arr.length;i++){
            int max = -1;
            int index = -1;
            for (int j=0;j< arr.length-i;j++){
                if(max<arr[j]){
                    max = arr[j];
                    index = j;
                }
            }
            swap(arr,index, arr.length-1-i);
        }
    }

    public static void bubbleSort(int[] arr){
        boolean swapped = false;
        for(int i=0;i<arr.length;i++){
            for(int j=1;j< arr.length-i;j++){
                if(arr[j-1]>arr[j]) {
                    swap(arr,j,j-1);
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
    }

    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void Twoswap(int[] arr1, int[] arr2, int a, int b){
        int temp = arr1[a];
        arr1[a] = arr2[b];
        arr2[b] = temp;
    }
}
