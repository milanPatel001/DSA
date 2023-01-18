import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class StacksQueues {
    private Stack<Integer> stack;
    private Queue<Integer> queue;

    public StacksQueues(){
        stack = new Stack<>();
        queue = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        StacksQueues st = new StacksQueues();
        st.stack.push(4);
        st.stack.push(3);
        st.stack.push(1);
        st.stack.push(2);
        st.stack.push(6);
        //st.reverseStack(st.stack,st.stack.size());
        st.sortStack(st.stack, st.stack.size());
        st.print();
    }

    public static int circularTour(int petrol[], int distance[]){
            //2. Optimized
        int prevLeftOverPetrol = 0, curr_petrol = 0, start = 0;

        for(int i=0; i<petrol.length; i++) {
            curr_petrol+= petrol[i]-distance[i];

            //prevLeftOverPetrol keeps track of petrol left whenever we arrive at a dead end, so that we don't have to calculate again by moving to start to complete a circular tour
            if(curr_petrol<0) {
                prevLeftOverPetrol+=curr_petrol;
                curr_petrol = 0;
                start = i+1;
            }
        }
        return prevLeftOverPetrol+curr_petrol>0?start:-1;

             /*1. Brute force
            for(int i=0;i<petrol.length;i++){

                int currPetrol = petrol[i];
                int nextDist = distance[i];

                if(currPetrol<nextDist) continue;

                int j = i+1;
                currPetrol -= nextDist;
                currPetrol += petrol[j];
                nextDist = distance[j];

                while(j!=i){
                    if(currPetrol<nextDist){
                        i = j-1;   //optimization
                        break;
                    }else{
                        currPetrol -= nextDist;
                        currPetrol += petrol[j];
                        nextDist = distance[j];
                    }
                    j = (j+1)%petrol.length;
                }
                if(i==j) return j;
            }
            return -1;

              */
        }


    public static boolean parenthesisChecker(String str){
        Stack<Character> st = new Stack<>();
        for(char i:str.toCharArray()){
            if(i=='(') st.push(')');
            if(i=='[') st.push(']');
            if(i=='{') st.push('}');



            if(i==')'){
                if(!st.isEmpty() && st.peek()==i)
                    st.pop();
                else return false;
            }

            if(i==']'){
                if(!st.isEmpty() && st.peek()==i)
                    st.pop();
                else return false;
            }

            if(i=='}'){
                if(!st.isEmpty() && st.peek()==i)
                    st.pop();
                else return false;
            }
        }
        return st.isEmpty();
    }

    public static int[] NGreatestR(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        ans[arr.length-1] = -1;

        //storing indexes
        st.push(arr.length-1);

        for(int i= arr.length-2;i>=0;i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                if(arr[i]<arr[i+1]){
                    st.push(i+1);
                    ans[i] = i+1;
                }
                else ans[i] = -1;
            }
            else ans[i] = st.peek();

            //st.push(i);
        }

        return ans;
    }

    public static int[] NGreatestL(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];

        ans[0] = -1;

        st.push(0);

        for(int i=1;i< arr.length;i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            if(st.isEmpty()) {
                if (arr[i] < arr[i - 1]) {
                    st.push(i - 1);
                    ans[i] = i - 1;
                } else ans[i] = -1;
            }
            else ans[i] = st.peek();

            // st.push(i);
        }

        return ans;
    }

    public static int[] nextSmallerElem(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        st.push(-1);

        for(int i= arr.length-1;i>=0;i--){
            while(st.peek()>=arr[i]) st.pop();

            if(st.peek()<arr[i]) ans[i] = st.peek();
            st.push(arr[i]);

        }
        return ans;
    }

    public static void firstNegInWin(int[] arr, int k){
        Queue<Integer> q = new ArrayDeque<>();
        int i = 0;
        int j = 0;
        while(j<k){
            if(arr[j]<0) q.add(arr[j]);
            j++;
        }
        j--;
        System.out.print(q.peek()+" ");

        while(j< arr.length-1){
            if(arr[i]<0) q.poll();
            if(arr[j+1]<0) q.add(arr[j+1]);
            if(q.isEmpty()) System.out.print(0+" ");
            else System.out.print(q.peek()+" ");
            j++;
            i++;
        }
    }

    public static void interleaveQueue(Queue<Integer> q){
         /*1. Using additional queue
         int half = q.size()/2;
         Queue<Integer> q2 = new ArrayDeque<>();
         for(int i=0;i<half;i++){
             q2.add(q.poll());
         }

         for(int i=0;i<half;i++){
             q.add(q2.poll());
             q.add(q.poll());
         }
          */
        //2. Using a stack
        int half = q.size()/2;
        Stack<Integer> st = new Stack<>();

        for(int i=0;i<half;i++) st.push(q.poll());

        for(int i=0;i<half;i++) q.add(st.pop());

        for(int i=0;i<half;i++) q.add(q.poll());

        for(int i=0;i<half;i++) st.push(q.poll());

        for(int i=0;i<half;i++){
            q.add(st.pop());
            q.add(q.poll());
        }

    }

    public static void celebProblem(int[][] arr){
        //1. Brute force
        //2. Using stack
        //3. Using two ptrs
    }

    public static void reverseFirstKElems(Queue<Integer> q,int k){
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<k;i++){
            st.push(q.poll());
        }

        while(!st.isEmpty()) q.add(st.pop());

        for(int i=0;i<q.size()-k;i++) q.add(q.poll());
    }

    static boolean checkRedundancy(String s) {
        // create a stack of characters
        Stack<Character> st = new Stack<>();
        char[] str = s.toCharArray();
        // Iterate through the given expression
        for (char ch : str) {

            // if current character is close parenthesis ')'
            if (ch == ')') {
                char top = st.peek();
                st.pop();

                // If immediate pop have open parenthesis '('
                // duplicate brackets found
                boolean flag = true;

                while (top != '(') {

                    // Check for operators in expression
                    if (top == '+' || top == '-'
                            || top == '*' || top == '/') {
                        flag = false;
                    }

                    // Fetch top element of stack
                    top = st.peek();
                    st.pop();
                }

                // If operators not found
                if (flag == true) {
                    return true;
                }
            } else {
                st.push(ch); // push open parenthesis '(',
            }                // operators and operands to stack
        }
        return false;
    }

    public int[] nextGreaterElement(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        st.push(-1);
        //ans[arr.length-1] = -1;

        for(int i=arr.length-1;i>=0;i--){
            while(!st.empty() && st.peek()<=arr[i]){
                 st.pop();
            }
            if(st.isEmpty()) ans[i] = -1;
            else ans[i] = st.peek();

            st.push(arr[i]);
        }
        return ans;
    }

    public void reverseStack(Stack<Integer> st, int size){
        if(st.isEmpty()) return;
        int top = st.pop();
        reverseStack(st,size);
        insertAtBottom(st, st.size(), top);
    }

    public void sortStack(Stack<Integer> st, int size){
        if(size==0) return;
        int top = st.pop();
        sortStack(st,size-1);
        insertIntoSortedStack(st,st.size(),top);
    }

    public void insertIntoSortedStack(Stack<Integer> st, int size, int data){
        if(size==0 || st.peek()>data) {
            st.push(data);
            return;
        }
        int top = st.pop();
        insertIntoSortedStack(st,size-1,data);
        st.push(top);
    }
    public void insertAtBottom(Stack<Integer> st, int size, int data){
        if(size==0){
            st.push(data);
            return;
        }
        int top = st.pop();
        insertAtBottom(st,size-1,data);
        st.push(top);
    }

    public void deleteMiddleStackelem(Stack<Integer> st, int count, int size){
        if(count==size/2) {
            System.out.println(st.pop());
            return;
        }
        int top = st.pop();
        deleteMiddleStackelem(st,count+1,size);
        st.push(top);
    }

    public int findMiddleElem(Stack<Integer> st,int size){
        if(st.isEmpty()) return -1;
        if(size/2==st.size()){
            return st.peek();
        }
        int top = st.peek();
        st.pop();
        int ans = findMiddleElem(st,size);
        st.push(top);

        return ans;
    }

    public void print(){
        while(!stack.isEmpty()) System.out.println(stack.pop());
    }
}
