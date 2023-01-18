import java.util.*;

/*
public class BinaryTrees {
    public int lastLevel = -1;
    static int nodeLevel = 0; //for kth ancestor

    public static class Pair2{
        Node node;
        int level;
        int vertical_dist;

        public Pair2(Node node) {
            this.node = node;
        }

        public Pair2(Node node, int level) {
            this.node = node;
            this.level = level;
        }

        public Pair2(Node node, int level, int vertical_dist) {
            this.node = node;
            this.level = level;
            this.vertical_dist = vertical_dist;
        }
    }

    public class Node{
        public int data;
        public Node leftChildChild;
        public Node rightChild;

        Node(int data){
            this.data = data;
            leftChildChild = null;
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
        BinaryTrees bt = new BinaryTrees();
        bt.root = bt.buildTree(bt.root);
        //bt.preOrderTraversal(bt.root);
        bt.verticalOrderTraversal();

    }


    public void kthAncestorNode(Node root, int node, int k, int level){
        if(root==null) return;
        if(root.data==node){
            nodeLevel = level;
            return;
        }


        kthAncestorNode(root.leftChildChild,node,k,level+1);
        kthAncestorNode(root.rightChild,node,k,level+1);

        if(nodeLevel-level==k) {
            System.out.println(root.data);
            nodeLevel = Integer.MAX_VALUE;
        }

    }


    boolean leafAtSameLevel(Node root,int level)
    {
        if(root==null) return true;
        if(root.leftChildChild==null && root.rightChild==null){
            if(lastLevel==-1) {
                lastLevel = level;
                return true;
            }
            int temp = lastLevel;
            lastLevel = level;
            return temp==level;
        }
        boolean c = leafAtSameLevel(root.leftChildChild,level+1);
        boolean d = leafAtSameLevel(root.rightChild,level+1);

        return c&&d;
    }

    public Node LCA(Node root, int n1, int n2){
        if(root==null) return null;
        if(root.data==n1 || root.data==n2) return root;

        Node leftChildSide = LCA(root.leftChild,n1,n2);
        Node rightSide = LCA(root.right,n1,n2);

        if(leftChildSide==null && rightSide!=null) return rightSide;
        if(leftChildSide!=null && rightSide==null) return leftChildSide;

        if(leftChildSide!=null && rightSide!=null) return root;
        return null;
    }


    public void bottomView(Node root){
        Queue<Pair> q = new ArrayDeque<>();
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();

        q.add(new Pair(root,0,0));
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(root.data);
        map.put(0,arr);

        while(!q.isEmpty()) {
            Pair top = q.poll();

            if(top.node.leftChild!=null){
                q.add(new Pair(top.node.leftChild, top.level+1, top.vertical_dist-1));

                ArrayList<Integer> arr1;
                if(map.containsKey(top.vertical_dist-1))
                    arr1 = map.get(top.vertical_dist-1);

                else
                    arr1 = new ArrayList<>();


                arr1.add(top.node.leftChild.data);
                map.put(top.vertical_dist-1,arr1);
            }

            if(top.node.right!=null){
                q.add(new Pair(top.node.right, top.level+1, top.vertical_dist+1));
                ArrayList<Integer> arr1;
                if(map.containsKey(top.vertical_dist+1))
                    arr1 = map.get(top.vertical_dist+1);
                else
                    arr1 = new ArrayList<>();



                arr1.add(top.node.right.data);

                map.put(top.vertical_dist+1,arr1);
            }

        }

        System.out.println(map);
    }

    public void topView(){
        Queue<Pair2> q = new ArrayDeque<>();
        Map<Integer,Boolean> dist_visited = new HashMap<>();
        PriorityQueue<Pair2> pq = new PriorityQueue<>((o1, o2) -> o2.vertical_dist-o1.vertical_dist);
        ArrayList<Integer> arr = new ArrayList<>();

        q.add(new Pair2(root,0,0));
        dist_visited.put(0,false);

        while(!q.isEmpty()) {

            Pair2 top = q.poll();

            if (!dist_visited.get(top.vertical_dist)){
                //System.out.print(top.node.data+" ");
                pq.add(top);
                dist_visited.put(top.vertical_dist, true);
            }

            if(top.node.leftChildChild!=null){
                if(!dist_visited.containsKey(top.vertical_dist-1))
                    dist_visited.put(top.vertical_dist-1,false);
                q.add(new Pair2(top.node.leftChildChild, top.level+1, top.vertical_dist-1));
            }

            if(top.node.rightChild!=null){
                if(!dist_visited.containsKey(top.vertical_dist+1))
                    dist_visited.put(top.vertical_dist+1,false);
                q.add(new Pair2(top.node.rightChild, top.level+1, top.vertical_dist+1));
            }
        }

        while(!pq.isEmpty()){
            arr.add(pq.poll().node.data);
        }

    }



    public void verticalOrderTraversal(){
        //map1 to map horizontal distance with list of nodes
        // map2 to map each node with their respective horizontal distance

        Map<Integer, ArrayList<Node>> map = new TreeMap<>();
        Map<Node,Integer> map2 = new HashMap<>();

        Queue<Node> q = new ArrayDeque<>();
        if(root==null) return;

        q.add(root);
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(root);
        map2.put(root, 0);

        while(!q.isEmpty()){
            Node front = q.peek();
            if(map.get(map2.get(front))==null){
                arr = new ArrayList<>();
                arr.add(front);
                map.put(map2.get(front),arr);
            }else{
                ArrayList<Node> ar = map.get(map2.get(front));
                ar.add(front);
                map.put(map2.get(front),ar);
            }

            q.poll();
            if(front.leftChildChild!=null){
                map2.put(front.leftChildChild,map2.get(front)-1);
                q.add(front.leftChildChild);
            }
            if(front.rightChild!=null){
                map2.put(front.rightChild,map2.get(front)+1);
                q.add(front.rightChild);
            }

        }

        for(var i:map.entrySet()){
            System.out.println(i.getKey()+" : " + i.getValue());
        }

    }



    public void zigZagTraversal(){
        //if leftChildToRight is true, just don't modify code
        // else don't print and push current level queue into stack and then empty the stack

        Queue<Node> q = new ArrayDeque<>();
        if(root==null) return;
        q.add(root);
        int level = 0;
        boolean leftChildToRight = true;
        Stack<Node> st = new Stack<>();
        while(!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node front = q.peek();

                if(!leftChildToRight) st.push(front);
                else System.out.print(front.data+" ");

                q.poll();
                if (front.leftChildChild != null) q.add(front.leftChildChild);
                if (front.rightChild != null) q.add(front.rightChild);
            }

            if(!leftChildToRight){
                while(!st.isEmpty()) System.out.print(st.pop().data + " ");
            }

            System.out.print("\n");
            leftChildToRight = !leftChildToRight;
            level++;
        }
    }

    public void kSumPaths(Node root, int k, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ans){
            if(root==null) return ;//ans;
            path.add(root.data);
            kSumPaths(root.leftChild,k,path,ans);
            kSumPaths(root.rightChild,k,path,ans);

            int sum = 0;
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i= path.size()-1;i>=0;i--){
                sum+=path.get(i);

                if(sum==k){
                    for(int j=i;j<path.size();j++) temp.add(path.get(j));
                    ans.add(temp);
                }
            }
            path.remove(path.size()-1);
            //return ans;
    }

    public Pair<Boolean,Integer> isSumTree(Node root){
        if(root==null) return new Pair<>(true,0);
        if(root.leftChildChild==null && root.rightChild==null) return new Pair<>(true,root.data);

        Pair<Boolean,Integer> leftChildAns = isSumTree(root.leftChildChild);
        Pair<Boolean,Integer> rightAns = isSumTree(root.rightChild);
        Pair<Boolean, Integer> ans = new Pair<>(true,0);
        if(((leftChildAns.second+ rightAns.second)==root.data) && leftChildAns.first && rightAns.first) ans.first = true;
        else ans.first = false;

        ans.second = leftChildAns.second + rightAns.second + root.data;
        return ans;
    }

    public Pair<Boolean,Integer> isBalanced(Node root){
        if(root==null) return new Pair<>(true,0);

        Pair<Boolean,Integer> leftChildAns = isBalanced(root.leftChildChild);
        Pair<Boolean,Integer> rightAns = isBalanced(root.rightChild);

        Pair<Boolean,Integer> ans = new Pair<>(true,0);
        if(leftChildAns.first && rightAns.first && Math.abs(leftChildAns.second-rightAns.second)<=1){
            ans.first = true;
        }else ans.first = false;

        ans.second = Math.max(leftChildAns.second,rightAns.second)+1;
        return ans;

    }

    public Node mirrorify(Node root){
        if(root==null) return null;
        Node mirror = new Node(root.data);
        mirror.rightChild = mirrorify(root.leftChildChild);
        mirror.leftChildChild = mirrorify(root.rightChild);
        return mirror;
    }

    public Pair<Integer,Integer> diameter(Node root){
        //#1. Calculate height separately
        //#2. Calculate height while returning

        if(root==null) return new Pair<>(0,0);

        Pair<Integer,Integer> leftAns = diameter(root.leftChild);
        Pair<Integer,Integer> rightAns = diameter(root.rightChild);

        int op1 = leftChildAns.first;
        int op2 = rightAns.first;
        int op3 = leftChildAns.second+rightAns.second+1;
        //int mix = height(root.leftChildChild)+height(root.rightChild)+1;
        Pair<Integer,Integer> ans = new Pair<>(0,0);

        ans.second = Math.max(leftChildAns.second,rightAns.second)+1;
        ans.first = Math.max(op1,Math.max(op2,op3));

        return ans;
    }

    public int countLeafs(Node root,int count){
        if(root==null) return 0;
        if(root.leftChildChild==null && root.rightChild==null){
            return ++count;
        }
        return countLeafs(root.leftChildChild,count)+countLeafs(root.rightChild,count);
    }

    public int height(Node root){
        if(root==null) return 0;
        int left = height(root.leftChild);
        int right = height(root.rightChild);

        return Math.max(leftChild,right)+1;
    }

    public void preOrderTraversal(Node root){
        if(root==null) return;
        System.out.println(root.data);
        preOrderTraversal(root.leftChildChild);
        preOrderTraversal(root.rightChild);
    }

    public void levelOrderTraversal(){
        if(root==null) return;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        System.out.print("\n");
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++) {
                Node front = q.peek();
                System.out.print(front.data + " ");
                q.poll();

                if (front.leftChildChild != null)
                    q.add(front.leftChildChild);
                if (front.rightChild != null)
                    q.add(front.rightChild);
             }
            System.out.print("\n");
            level++;
            }
        System.out.println("LEVELS: "+level );
    }

    public ArrayList<Integer> reverseLevelOrder(Node node)
    {   //Insert right and then leftChild
        //#1. and then just reverse the arr.
        //#2. Use stack to catch popped up from queue and then just pop from the stack

        ArrayList<Integer> arr = new ArrayList<>();
        if(node==null) return arr;
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while(!q.isEmpty()){
            Node front = q.peek();
            arr.add(front.data);
            q.poll();


            if(front.rightChild!=null)
                q.add(front.rightChild);
            if(front.leftChildChild!=null)
                q.add(front.leftChildChild);

        }
        Collections.reverse(arr);
        return arr;
    }

    public Pair isSumTree(Node root){
        if(root==null) return new Pair(0,true);
        if(root.leftChild==null && root.right==null) return new Pair(root.data,true);

        Pair leftChildSide = isSumTree(root.leftChild);
        Pair rightSide = isSumTree(root.right);
        Pair a = new Pair(0,false);
        if(a.sum == root.data && leftChildSide.ans && rightSide.ans){
            a.setAns(true);
        }
        a.setSum(leftChildSide.sum+ rightSide.sum+ root.data);
        return a;
    }




    public Node buildTree(Node root){
        System.out.println("Enter data: ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        root = new Node(data);
        if(data==-1) return null;

        System.out.println("Enter leftChild child of "+data);
        root.leftChildChild = buildTree(root.leftChildChild);
        System.out.println("Enter right child of "+data);
        root.rightChild = buildTree(root.rightChild);
        return root;
    }

    @Override
    public String toString() {
        return "BinaryTrees{" +
                "root=" + root.data +
                '}';
    }

    public void traverseLeaf(Node root){
        if(root==null) return;
        if(root.leftChild==null && root.right==null) {
            System.out.print(root.data+" ");
            return;
        }

        traverseLeaf(root.leftChild);
        traverseLeaf(root.right);
    }

    public void traverseLeft(Node root){
        if(root==null || (root.leftChild==null && root.right==null)) return;
        System.out.print(root.data+" ");
        if(root.leftChild!=null){
            traverseLeft(root.leftChild);
        }else traverseLeft(root.right);
    }

    public void traverseRight(Node root){
        if(root==null || (root.leftChild==null && root.right==null)) return;

        if(root.right!=null){
            traverseRight(root.right);
        }else traverseRight(root.leftChild);

        System.out.print(root.data+" ");
    }


    public void boundaryTraversal(Node root){
        System.out.print(root.data+" ");
        traverseLeft(root.leftChild);
        traverseLeaf(root.leftChild);
        traverseLeaf(root.right);
        traverseRight(root.right);

    }
}
*/