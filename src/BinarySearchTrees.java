import java.util.Arrays;

public class BinarySearchTrees {
    public class Node{
        public int data;
        public Node leftChild;
        public Node rightChild;

        Node(int data){
            this.data = data;
            leftChild = null;
            rightChild = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public Node root;

    public static void main(String[] args) {
        int[] arr = {40,30,32,35,80,90,100,120};
        preToPost(arr);
    }

    public void inorderSuccessor(int key){
        Node succ = new Node(-1);
        Node curr = root;
        while(curr!=null){
            if(key<= curr.data){
                curr = curr.leftChild;
            }else{
                succ = curr;
                curr = curr.rightChild;
            }
        }
    }

    public void inorderPredecessor( int key){
        Node pre = new Node(-1);
        Node curr = root;
        while(curr!=null){
            if(key>= curr.data){
                curr = curr.rightChild;
            }else{
                pre = curr;
                curr = curr.leftChild;
            }
        }
    }

    public int[] minMax(){
        int[] arr = new int[2];
        Node min = root;
        Node max = root;
        while(min!=null||max!=null){
            if(min!=null){
                arr[0] = min.data;
                min = min.leftChild;
            }
            if(max!=null){
                arr[1] = max.data;
                max = max.rightChild;
            }
        }
        return arr;
    }

    public boolean find(int key){
        if(root.data==key) {
            return true;
        }

        Node current = root;
        while(current!=null){
            if(current.data == key) return true;
            if(key < root.data){
                current = current.leftChild;
            }else{
                current = current.rightChild;
            }
        }
        return false;
    }


    public boolean checkBst(Node root, int max, int min){
        if(root==null) return true;

        boolean leftAns = checkBst(root.leftChild, root.data,min);
        boolean rightAns = checkBst(root.rightChild,max,root.data);

        return leftAns && rightAns && root.data <= max && root.data >= min;

    }

    public Node prev = null;
    public void populateInorderSuccessor(Node root){
        if(root==null) return;

        populateInorderSuccessor(root.leftChild);

        if(prev!=null) //prev.next = root;

            prev = root;

        populateInorderSuccessor(root.rightChild);
    }

    public Node LCA(Node root, int n1, int n2){
        if(root==null) return null;

        Node curr = root;
        while(curr!=null){
            if(curr.data<n1 && curr.data<n2) curr = curr.rightChild;
            else if(curr.data>n1 && curr.data>n2) curr = curr.leftChild;
            else return curr;
        }
        return root;
    }

    public void kthSmallest(Node root,int ans,int k){
        if(root==null) return;
        kthLargest(root.leftChild,ans,k);

        k--;
        if(k==0) {
            ans =  root.data;
            return;
        }

        kthLargest(root.rightChild,ans,k);

    }

    public void kthLargest(Node root,int ans,int k){
        if(root==null) return;
        kthLargest(root.rightChild,ans,k);

        k--;
        if(k==0) {
            ans =  root.data;
            return;
        }

        kthLargest(root.leftChild,ans,k);

    }

    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void rec_reverse(int[] arr,int i, int j){
        if(i>=j) return;
        rec_reverse(arr,i+1,j-1);
        swap(arr,i,j);
    }

    public static void preToPost(int[] arr){
        int leftTreeMax = -1;

        for(int i=1;i< arr.length;i++){
            if(arr[i]>arr[0]){
                leftTreeMax = i-1;
                break;
            }
        }

        rec_reverse(arr,1,arr.length-1);

        rec_reverse(arr,1,arr.length-leftTreeMax-1);
        rec_reverse(arr,arr.length-leftTreeMax, arr.length-1);

        rec_reverse(arr,0,arr.length-1);

        System.out.println(Arrays.toString(arr));

    }
    /*
      public Node deleteNode(Node root, int data){
                if(root==null) return null;
                if(root.data==data){
                    if(root.leftChild==null && root.rightChild==null){
                        root.data = -1;
                        return null;
                    }
                    else if(root.leftChild!=null && root.rightChild==null){
                        Node temp = root.leftChild;
                        root.data = -1;
                        return temp;
                    }
                    else if(root.rightChild!=null && root.leftChild==null){
                        Node temp = root.rightChild;
                        root.data = -1;
                        return temp;
                    }
                    else{

                    }
                }
            }
     */

}
