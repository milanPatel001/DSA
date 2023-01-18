import java.util.*;

public class Practise {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,6};
        System.out.println(search(arr, arr.length,6));
        }
        public static boolean search(int[] arr,int size,int key){
            if(size==0) return false;
            if(arr[size-1]==key) return true;
            return search(arr,size-1,key);
    }

}