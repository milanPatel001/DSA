import java.util.HashSet;
import java.util.Set;

public class LinkedList {
    private class Node{
        int data;
        Node next;
        Node prev;

        Node(int n){
            data = n;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }

    public Node head;
    public Node tail;

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();



        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(40);



        System.out.println(ll.deleteGreaterRight(ll.head));

    }

    public Node deleteGreaterRight(Node head){
        if(head.next==null) return head;

        Node r = deleteGreaterRight(head.next);

        if(head.data >= r.data) {
            head.next = r;
            return head;
        }
        return r;
    }

    int countTripletDLL(Node head,int k){
        int count = 0;
        Node curr = head;
        Set<Integer> set = new HashSet<>();

        while(curr.next!=null){
            curr = curr.next;
        }

        Node tail = curr;
        Node last = tail;


        curr = head;
        while(curr.next.next!=last){
            Node ahead = curr.next;
            while(ahead!=last){
                if(curr.data+ahead.data+last.data<k){
                    ahead = ahead.next;
                }else if(curr.data+ahead.data+last.data>k){
                    last = last.prev;
                }else{
                    count++;
                    ahead = ahead.next;
                    last = last.next;
                }
            }
            last = tail;
            curr = curr.next;
        }


        return count;
    }

    int getNthFromLast(Node head, int n)
    {
        Node fastptr = head;
        Node slowptr = head;

        int fast = 1;
        int slow = 1;

        while(fastptr==null && fastptr.next==null){
            fastptr = fastptr.next.next;
            slowptr = slowptr.next;

            fast+=2;
            slow+=1;
        }

        int size = 0;
        if(fastptr!=null){
            size = fast;
        }else{
            size = fast-1;
        }

        if(size-n+1==slow) return slowptr.data;

        if(size-n+1<slow){
            slowptr = head;
            slow = 1;
        }

        while(slowptr!=null){
            if(size-n+1-slow==0){
                break;
            }
            slow++;
            slowptr = slowptr.next;
        }
        return slowptr.data;
    }

    public void addOne(){
        reverse();
        Node current = head;

        int carry = 0;
        if(current.data!=9){
            current.data += 1;
        }else {
            while (current!=null && current.data == 9) {
                current.data = 0;
                current = current.next;
            }
            carry = 1;
        }
        if(current!=null)
            current.data+=carry;

        reverse();


        if(current==null){
            Node node = new Node(1);
            node.next = head;
            head = node;
        }
        //return head;
    }

    public static void intersectionPoint(Node head1, Node head2){
        Node curr1 = head1;
        Node after_curr1 = head1.next;
        Node curr2 = head2;
        Node after_curr2 = head2.next;

        while(after_curr1!=null && after_curr2 !=null){
            if(after_curr1==after_curr2) return;

                curr1.next = null;
                curr1 = after_curr1;
                after_curr1 = after_curr1.next;


                curr2.next = null;
                curr2 = after_curr2;
                after_curr2 = after_curr2.next;

        }

        if(after_curr1!=null) {
            while (after_curr1 != null) {
                curr1 = after_curr1;
                after_curr1 = after_curr1.next;
            }
            //return curr1.data;
        }

        if(after_curr2!=null) {
            while (after_curr2 != null) {
                curr2 = after_curr2;
                after_curr2 = after_curr2.next;
            }
            //return curr2.data;
        }
        //return -1;

    }

    public static Node findIntersection(Node head1, Node head2)
    {
        Node curr1 = head1;
        Node curr2 = head2;

       // Node head = new Node(-1);  new Linked list
        //Node curr = head;

        while(curr1!=null && curr2!=null){
            if(curr1.data>curr2.data) curr2 = curr2.next;
            else if(curr1.data<curr2.data) curr1 = curr1.next;
            else{

               // curr.next = new Node(curr1.data);
              //  curr = curr.next;

                curr1 = curr1.next;
                curr2 = curr2.next;

            }
        }
       // return head.next;
        return null;
    }

    public void addTwoNumbers(Node head1, Node head2){
        //Dont forget to reverse both lls
        Node current1 = head1;
        Node current2 = head2;
        LinkedList ll = new LinkedList();

        int carry = 0;
        int sum = 0;
        while(current1!=null && current2!=null){
                sum = current1.data+ current2.data + carry;
                ll.add(sum%10);
                carry = sum/10;

            current1 = current1.next;
            current2 = current2.next;
        }

        while(current1!=null) {
                sum = current1.data+carry;
                ll.add(sum%10);
                carry = sum/10;

            current1 = current1.next;
        }


        while(current2!=null){
            sum = current2.data+carry;
            ll.add(sum%10);
            carry = sum/10;

            current2 = current2.next;
        }
        //Dont forget to reverse ans ll

    }

    public void reverse() {
        Node prev = null;
        Node curr = head;
        Node next = null;
        tail = curr;

        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public Node rec_reverse(Node head){
        if(head==null||head.next==null) return head;
        Node rest = rec_reverse(head.next);
        head.next.next = head;
        head.next = null;

        return rest;
    }
    public boolean detect_loop(){
        //Using fast and slow pointers
        Node fast = head;
        Node slow = head;
        if(head==null||head.next==null) return false;

        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) return true;
        }
        return false;
    }


    public void removeDuplicateUnSorted() {
        //1. Use set and two pointers (less space than creating new ll)
        Set<Integer> set = new HashSet<>();
        if(head==null || head.next==null) return;
        Node curr = head;
        Node after = head.next;
        set.add(curr.data);

        while(after!=null){
            if(!set.contains(after.data)){
                set.add(after.data);
                curr = after;
                after = after.next;
            }else {
                while (after.next!=null && set.contains(after.next.data) ) {
                    after = after.next;
                }
                Node temp = after;
                after = after.next;
                temp.next = null;
                curr.next = after;
            }
        }
        //return head;

        /*2. create new linked list
        Set<Integer> set = new HashSet<>();
        Node curr = head;

        LinkedList ll = new LinkedList();
        while(curr!=null){
            if(!set.contains(curr.data)){

                ll.add(curr.data);
                set.add(curr.data);
            }
            curr = curr.next;
        }
        //return ll.head;
    }
         */
    }
    public void removeDuplicateSorted(){
        if(head==null || head.next==null) return;
        Node curr = head;
        Node after = head.next;

        while(after!=null){
            if(curr.data!=after.data){
                curr.next = after;
                curr = after;
            }
            after = after.next;
        }

        curr.next = null;
    }

    public void remove_loop(){
        //#1. Multiply data by -1 and then traverse linear
        //#2. First Detect loop and then reset the slow pointer
        Node fast = head;
        Node slow = head;
        if(head==null||head.next==null) return;

        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow){
                break;
            }
        }

        Node p = head;
        while(p.next!=slow.next){
            p = p.next;
            slow = slow.next;
        }

        //while(slow.next!=p) slow = slow.next;
        slow.next = null;
    }


    public Node reverseGroups(Node head, int k){
        //#1. Using 3 pointers and a temp pointer
        //#2. Using recursion

        if(head==null) return head;

        Node prev = null;
        Node next = null;
        Node curr = head;

        int i=0;
        while(i<k && curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }

        if(next!=null)
            head.next = reverseGroups(next,k);

        return prev;
    }

    public void add(int n){
        Node node = new Node(n);
        if(head==null) {
            head = node;
        }else{
            Node first = head;
            while(first!=tail) first = first.next;
            first.next = node;
        }
        tail = node;
    }

    public Node getHead(){
        return head;
    }

    public void print()
    {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }
}
