import java.util.Arrays;
import java.util.Collections;

public class SearchingSorting {
    public static void main(String[] args) {
        int[] arr = {1,0,3};

        int[] ans = productArrayPuzzle(arr);
        for (int i:ans){
            System.out.println(i);
        }
    }

    public static int floorSqrt(int x)
    {
        if(x==1) return 1;
        int s = 0;
        int e = x/2;
        int ans = 0;

        while(s<=e){
            int mid = s+((e-s)/2);
            if(mid*mid==x) return mid;
            else if(mid*mid<x) {
                s = mid+1;
                ans = mid;

            }
            else e = mid-1;
        }
        return ans;
    }

    public static int countTripletsSmallerThanTarget(int[] arr, int target){
        Arrays.sort(arr);
        int count = 0;
        for(int i=0;i<arr.length-2;i++){
            int j=i+1;
            int k = arr.length-1;
            while(j<k){
                if(arr[i]+arr[j]+arr[k]>=target) k--;
                else{
                    count += k-j;
                    i++;
                }
            }
        }
        return count;
    }

    public static int[] productArrayPuzzle(int[] arr){
        int[] ans = new int[arr.length];
        int multiply = 1;
        boolean containsZero = false;
        for(int i:arr){
            if(i!=0) multiply*=i;
            else containsZero = true;
        }

        for(int i = 0;i<arr.length;i++){
            if(!containsZero){
                ans[i] = multiply/arr[i];
            }else{
                if(arr[i]==0) ans[i] = multiply;
            }
        }

        return ans;
    }

    public static boolean pairDiff(int[] arr, int d){
        //#1. Brute force
        //#2. Use set - O(n) space, O(n) time
        //#3. Sort and 2 pointers - O(1) space O(nlog(n)) time
        Arrays.sort(arr);
        int i=0,j=1;
        while(i<j){
            if(arr[i]-arr[j]==d) return true;
            if(arr[j]-arr[i]>d){
                j++;
            }else{
                i++;
            }
        }
        return false;
    }

    public static int majorityElem(int[] arr){
        //#1. Sort and count
        //#2. Use hashmap/arr to store count
        //#3. Boyer-Moore's voting algorithm (O(1) space)

        int n = arr.length;
        int candidate = -1, votes = 0;
        // Finding majority candidate
        //Considering first element is the major element
        for (int i = 0; i < n; i++) {
            if (votes == 0) {          //if votes = 0, that we found new major candidate
                candidate = arr[i];
                votes = 1;
            }
            else {
                if (arr[i] == candidate) //If curr elem is major candidate, inc votes else decrease
                    votes++;
                else
                    votes--;
            }
        }
        int count = 0;
        // Checking if majority candidate occurs more than n/2
        // times
        for (int i = 0; i < n; i++) {
            if (arr[i] == candidate)
                count++;
        }

        if (count > n / 2)
            return candidate;
        return -1;
    }

    public static int[] missingAndRepeating(int[] arr){
        //#1. Sort and index matching
        //#2. Use xor or hashmap/array to store count or use Set
        //#3. Modify indexes to catch repeating and missing
        int[] ans = new int[2];

        for(int i=0;i<arr.length;i++){
            int index = Math.abs(arr[i])-1;

            if(arr[index]<0){
                ans[0] = Math.abs(arr[i]);
            }
            else arr[index] *= -1;
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0){
                ans[1] = i+1;
                break;
            }
        }

        return ans;
    }

    public static int rotatedSortedSearch(int[] arr, int x){
        //#1. Linear Search
        //#2. Find pivot and binary Search
        int smallest = pivot(arr);
        if(x==arr[0]) return 0;
        if(x>arr[0]) return binarySearch(arr,0,smallest-1,x);
        else return binarySearch(arr,smallest,arr.length-1,x);

    }

    public static int pivot(int[] arr){
        int s = 0;
        int e = arr.length-1;
        int mid = s + (e-s)/2;

        while(s<e){
            if(arr[mid]>=arr[0]) s = mid+1;
            else e = mid;

            mid = s + (e-s)/2;
        }

        return s;
    }
    public static int[] firstlast(int[] arr, int x){
        //#1. Use linear search
        //#2. Use binary search
        int[] ans = {0,0};
        int s = 0;
        int e = arr.length-1;


        int mid = s+ (e-s)/2;
        while(s<=e){
            if(arr[mid]==x){
                ans[1] = mid;
                s = mid+1;
            }else if(arr[mid]<x) s = mid+1;
            else e = mid-1;

            mid = s+ (e-s)/2;
        }

         s = 0;
        e = arr.length-1;
        mid = s+ (e-s)/2;
        while(s<=e){
            if(arr[mid]==x){
                ans[0] = mid;
                e = mid-1;
            }else if(arr[mid]<x) s = mid+1;
            else e = mid-1;

            mid = s+ (e-s)/2;
        }
        return ans;

    }

    public static int binarySearch(int[] arr,int s, int e,int x) {
        //#1. Use linear search
        //#2. Use binary search

        int mid = s + (e - s) / 2;

        while (s <= e) {
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] < x) s = mid + 1;
            else e = mid - 1;

            mid = s + (e - s) / 2;
        }
        return -1;
    }

}
