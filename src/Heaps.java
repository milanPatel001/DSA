import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Heaps {

        int[] arr = new int[10];
        int size = 0;

        public void insert(int data){
            int index = size++;
            arr[index] = data;

            while(index>0){
                int parent = index/2;
                if(arr[parent]>arr[index]){
                    swap(arr,parent,index);
                    index = parent;
                }else return;
            }
        }

        public int remove(){
            if(size==0) return -1;

            int top = arr[0];
            swap(arr,0,size-1);
            arr[size-1] = 0;
            size--;

            int index = 0;
            while(index<size){
                int childIndex1 = 2*index+1;
                int childIndex2 = 2*index+2;

                int smallerChild;

                if(childIndex1<size && childIndex2>=size){
                    smallerChild = childIndex1;
                }else if(childIndex1>=size && childIndex2<size){
                    smallerChild = childIndex2;
                }else if(childIndex1<size && childIndex2<size) {
                    smallerChild = arr[childIndex1] > arr[childIndex2] ? childIndex2 : childIndex1;
                }else return top;

                if(arr[index]>arr[smallerChild]) {
                    swap(arr, smallerChild, index);
                }
                index = smallerChild;
            }

            return top;
        }

    public void heapify(int [] arr, int size, int index){
        //similar to remove(). just rec is used
        //use a for loop on every elem  to heapify of an arr
        int smallest = index;
        int leftChildIndex = 2*index+1;
        int rightChildIndex = 2*index+2;

        if(leftChildIndex<size && arr[leftChildIndex]<arr[smallest]) smallest = leftChildIndex;
        if(rightChildIndex<size && arr[rightChildIndex]<arr[smallest]) smallest = rightChildIndex;

        if(smallest!=index){
            swap(arr,index,smallest);
            heapify(arr,size,smallest);
        }

    }

        public void swap(int[] arr,int a,int b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }


    public void maximumInSubArray(int[] arr,int k){
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for(int i=0;i<k;i++){
            if(q.isEmpty()) q.add(arr[i]);
            if(!q.isEmpty() && q.getFirst()<arr[i]) q.addFirst(arr[i]);
            else {
                while (!q.isEmpty() && arr[i] >= q.getLast()) {
                    q.removeLast();
                }
                q.add(arr[i]);
            }

        }
        System.out.print(q.peek()+" ");

        int ahead = k;
        int prev = 0;

        while(ahead<arr.length){
            if(arr[prev]==q.getFirst()) q.removeFirst(); //removing out of window element

            if(!q.isEmpty() && q.getFirst()<arr[ahead]) q.addFirst(arr[ahead]);

            else {
                while (!q.isEmpty() && arr[ahead] >= q.getLast()) {
                    q.removeLast();
                }
                q.add(arr[ahead]);
            }

            ahead++;
            prev++;
            System.out.print(q.peek()+" ");
        }
    }

    public void heapSort(int[] arr){
          int size = arr.length;
          while(size>0){
              swap(arr,0,size-1);
              size--;
              heapify(arr,size,0);
          }
    }


    public static void main(String[] args) {
        Heaps heap = new Heaps();
        heap.insert(2);
        heap.insert(1);
        heap.insert(3);
        heap.insert(5);
        heap.insert(4);
        heap.insert(10);
        heap.insert(7);
        heap.insert(9);
        heap.insert(8);
        System.out.println(heap.remove());




        System.out.println(Arrays.toString(heap.arr));

    }
}

    /*
    public ArrayList<Integer> arr;
    public int size;

    public Heaps(){
        arr = new ArrayList<Integer>();
        size = 0;
    }

    public static void main(String[] args) {
        Heaps h = new Heaps();
        h.insert(2);
        h.insert(1);
        h.insert(0);
        h.insert(4);
        h.insert(3);


        System.out.println(h.arr);
    }

    public void remove(){
        arr.set(0,arr.get(size-1));
        int i = 0;
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largerIndex;
        while(i<size && leftChild<size && rightChild<size){
            largerIndex = arr.get(leftChild)>arr.get(rightChild)?leftChild:rightChild;
            swap(arr,largerIndex,i);
            i = largerIndex;
            leftChild = 2*i+1;
            rightChild = 2*i+2;
        }
        arr.remove(size-1);
        size--;
    }

    public void insert(int data){
        arr.add(data);
        size++;
        int parentIndex = size/2;
        int i = size-1;
        while(i>=0  && data>arr.get(parentIndex)){
            swap(arr,i,parentIndex);
            i = parentIndex;
            parentIndex = parentIndex/2;
        }
    }

    public void swap(ArrayList<Integer> arr, int i, int j){
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }
}

     */



