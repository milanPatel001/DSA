import java.util.*;

public class Array {
    public static void main(String[] args) {
        int[] arr = {0,0,5,5,0,0};

        System.out.println(printZeroSumSubArr(arr));

    }

    public static int minMerge(int[] arr){
        int i = 0;
        int j = arr.length-1;

        int numOfMerge = 0;
        while(i<j){
            if(arr[i]==arr[j]){
                i++;
                j--;
            }
            else if(arr[i]<arr[j]){
                arr[i+1] = arr[i]+arr[i+1];
                i++;
                numOfMerge++;
            }else{
                arr[j-1] = arr[j]+arr[j-1];
                j--;
                numOfMerge++;
            }

        }
        return numOfMerge;
    }

    public static int minSwap (int arr[], int n, int k) {
        int min = Integer.MAX_VALUE;
        int swaps = 0;

        int size = 0;
        for(int x:arr){
            if(x<=k) size++;
        }

        int i=0;
        int j=0;

        while(j-i<size){
            if(arr[j]>k) swaps++;

            j++;
        }
        j--;
        min = Math.min(min,swaps);

        while(j<n-1){
            if(arr[++j]<=k) swaps--;
            else swaps++;

            if((arr[i]>k && arr[j]>k) || (arr[i]<=k && arr[j]<=k)){
                if(arr[i]<=k) swaps++;
                else swaps--;
            }
            i++;
            min = Math.min(min,swaps);
        }

        return min;
    }

    public static int trappingRainWater(int[] arr){
     int[] greatest_left = new int[arr.length];
     int[] greatest_right = new int[arr.length];

     greatest_left[0] = arr[0];
     greatest_right[arr.length-1] = arr[arr.length-1];

     int i=1;
     int j=arr.length-2;

     while(i<arr.length){
         greatest_left[i] = Math.max(arr[i],greatest_left[i-1]);
         greatest_right[j] = Math.max(arr[j],greatest_right[j+1]);
         i++;
         j--;
     }
     int sum = 0;
     for (int k=0;k<arr.length;k++){
         sum+= Math.min(greatest_left[k],greatest_right[k])-arr[i];
     }
        return sum;
    }

    public static ArrayList<int[]> printZeroSumSubArr(int[] arr){
        //map to store sum and their list of indexes where this sum is obtained
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();

        int sum = 0;
        ArrayList<int[]> ans = new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum==0){
                ans.add(new int[]{0,i});
            }
            ArrayList<Integer> temp = new ArrayList<>();
            if(map.containsKey(sum)){
                //traversing the list of indexes corresponding to sum
                //if j->i sum is found in map and j->k sum's is zero then k->i sum's will also be zero
                for(int j=0;j<map.get(sum).size();j++){
                    temp = map.get(sum);
                    ans.add(new int[]{temp.get(j)+1,i});
                }
                map.get(sum).add(i);
            }else{
                temp.add(i);
                map.put(sum,temp);
            }
        }
        return ans;
    }

    public static int fourSum(int[] arr,int k){
        Arrays.sort(arr);

        int count = 0;
        for(int i=0;i<arr.length-2;i++){
            for(int j=i+1;j<arr.length-1;j++){
                int low = j+1;
                int high = arr.length-1;

                while(low<high){
                    if(arr[low]+arr[high]+arr[i]+arr[j]<k) low++;
                    else if(arr[low]+arr[high]+arr[i]+arr[j]>k) high--;
                    else {
                        low++;
                        high--;
                        count++;
                    }
                }


            }
        }
        return count;
    }

    public static boolean threeSum(int[] arr, int k){
        //1. Using set (same as 2sum, just more loops)
        /*
        Set<Integer> set = new HashSet<>();
        int leftOverSum;
        for(int i=0;i<arr.length;i++){
            set.clear();
            leftOverSum = k-arr[i];
            for(int j=0;j<arr.length;j++){
                if(arr[j]!=arr[i]) {
                    if (set.contains(leftOverSum - arr[j])) return true;
                    set.add(arr[j]);
                }
            }
        }
        return false;
        */

        //2. Using two pointers in a loop (similar to dnf sort/binary search)
        // (O(1)space)
        Arrays.sort(arr);

        for(int i=0;i<arr.length;i++){
            int low = i+1;
            int high = arr.length-1;
            while(low<high){
                if(arr[i]+arr[low]+arr[high]==k) return true;
                else if(arr[i]+arr[low]+arr[high]<k){
                    low++;
                }else{
                    high--;
                }
            }
        }
        return false;
    }

    public static int smallestSubArray(int[] arr,int x){
        //Sliding window technique
        int i=0;
        int j = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while(sum<x && j<arr.length){
            sum += arr[j];
            j++;
        }

        minLength = j-i+1;
        j--;
        while(j<arr.length){
            if(sum>x){
                minLength = Math.min(j-i+1,minLength);
                sum-=arr[i];
                i++;
            }
            else {
                j++;
                if(j<arr.length)
                    sum+=arr[j];

            }
        }

        return minLength;
    }

    public static int maxProductSubArray(int[] arr){
        //1. Bruteforce
        //2. product from lhs and rhs
        int lhs_product = 1;
        int rhs_product = 1;

        int max = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){
            lhs_product *= arr[i];
            rhs_product *= arr[arr.length-1-i];

            max = Math.max(lhs_product,max);
            max = Math.max(rhs_product,max);

            if(lhs_product==0) lhs_product=1;
            if(rhs_product==0) rhs_product=1;

        }

        return max;
    }

    public static int maxSumSubArray(int[] arr){
        //1. Brute force
        //2. Divide the arr in half and find max in both halves recursively
        //3. Kadane's Algorithm
        /*
        Kadane’s Algorithm can be viewed both as a greedy and DP.
        As we can see that we are keeping a running sum of integers and
        when it becomes less than 0, we reset it to 0 (Greedy Part). This
        is because continuing with a negative sum is way more worse than
        restarting with a new range. Now it can also be viewed as a DP,
        at each stage we have 2 choices: Either take the current element
        and continue with previous sum OR restart a new range.
        These both choices are being taken care of in the implementation.
         */
        int sum = 0;
        int maxSumSoFar = arr[0];

        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            maxSumSoFar = Math.max(maxSumSoFar,sum);
            if(sum<0) sum = 0;

        }
        return maxSumSoFar;
    }

    public static boolean isSubsetArray(int[] arr1, int[] arr2){
        //1. Count
        //2. Sort and two pointers
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i=0;
        int j =0;

        if(arr2.length> arr1.length) return false;

        while(j< arr2.length && i< arr1.length){
            if(arr1[i]==arr2[j]){
                i++;
                j++;
            }
            else i++;
        }
        if(j == arr2.length)return true;
        return false;

    }

    public static int chocolateDist(int[] arr, int k){
        Arrays.sort(arr);
        int min_dif = Integer.MAX_VALUE;
        int i = 0;
        int j = i+k-1;
        while(j<arr.length){
            min_dif = Math.min(min_dif,arr[j]-arr[i]);
            j++;
            i++;
        }
        return min_dif;
    }

    public static int bestTimeStockSell2(int[] arr){
        int profit = 0;
        int sell = Integer.MIN_VALUE;
        int buy = Integer.MAX_VALUE;


        for(int i=0;i<arr.length;i++){
            if(buy>arr[i]){
                buy = arr[i];

            }
            sell = Math.max(sell,arr[i]-buy);
        }

        profit+=sell;

        return profit;
    }

    public static boolean subset(int[] arr1, int[] arr2){
        Map<Integer,Integer> map = new HashMap<>();

        for(int i:arr1){
            map.put(i,map.containsKey(i)?map.get(i)+1:1);
        }

        for(int j:arr2){
            if(map.containsKey(j) && map.get(j)!=0) map.put(j,map.get(j)-1);
            else return false;
        }
        return true;
    }

    public static int longestConsecutive(int[] arr){
        //#1. Store each element in a hashmap/large array and then check continuity
        /*
        HashMap<Integer,Integer> map = new HashMap<>();

        int maxCount = 1;
        for(int i=0;i< arr.length;i++){
            if(map.containsKey(arr[i])) continue;
            if(map.containsKey(arr[i]-1) || map.containsKey(arr[i]+1)){
                int sum = map.getOrDefault(arr[i]-1,0) + map.getOrDefault(arr[i]+1,0) + 1;

                int ahead = arr[i];
                while(map.containsKey(ahead+1)){
                        map.put(ahead+1,sum);
                        ahead++;

                }

                map.put(arr[i],sum);

                int previous = arr[i];
                while(map.containsKey(previous-1)){
                    map.put(previous-1,sum);
                    previous--;

                }

                maxCount = Math.max(maxCount,sum);
            }
            else{
                map.put(arr[i],1);
            }
        }
        return maxCount;
         */

        //#2. Create a temp array(like a count array) and then use min and max
        //as starting and ending index to find continuity.
        /*
         int[] temp= new int[1000000];

             int maxIndex = 0;
               int minIndex = 0;
             for(int i=0;i<N;i++){
                temp[arr[i]]=1;
              maxIndex=Math.max(maxIndex, arr[i]);
              minIndex = Math.min(minIndex,arr[i]);
             }
              int maxCount=0; int count=0;
              for(int i=minIndex;i<=maxIndex;i++){
             if(temp[i]!=0)
               count++;
            else
               count=0;
           maxCount=Math.max(maxCount, count);
             }
          return maxCount;
         */


        //#3. Sort and use a loop
        int count = 1;
        Arrays.sort(arr);
        int max = 1;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]+1==arr[i+1]) {
                count++;
                max = Math.max(max,count);
            }else if(arr[i]==arr[i+1]){
                continue;
            }else count = 1;
        }

        return max;
    }

    public static ArrayList<Integer> factorial(int n){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        int carry = 0;
        for(int i = 2;i<=n;i++){
            for(int j=0;j<arr.size();j++){
                int val = arr.get(j)*i+carry;
                arr.remove(j);
                arr.add(j,val%10);
                carry = val/10;
            }
            while(carry!=0){
               arr.add (carry%10);
                carry/=10;
            }
        }
        Collections.reverse(arr);
        return arr;
    }

    public static boolean subZero(int[] arr){
        //#1. Brute Force
        //#2. Optimized using set

        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for(int i=0;i< arr.length;i++){
            if(arr[i]==0) return true;
            sum+=arr[i];
            if(set.contains(sum) || sum==0) return true;
            set.add(sum);
        }
        return false;
    }

    public static void altNeg(int[] arr){
      //#1. Use two queues
      //#2. Brute force
      //#3. 4 pointers (without extra space) - two for swapping and two for flagging first positive and last negative

      /*Moving negative to one side inorder
      int last_neg = arr.length-1;
      if(arr.length==1) return;

      int first_pos = 0;

      while(first_pos<last_neg){

          while(arr[last_neg]>=0){
              last_neg--;
          }
          while(arr[first_pos]<0){
              first_pos++;
          }
          int next = last_neg;
          int prev = last_neg-1;

        while(prev>=first_pos) {
            if (arr[next] < 0 && arr[prev] >= 0) {
                swap(arr, next, prev);
            }
            next--;
            prev--;
        }
        first_pos++;
      }
      //Now alternating
      first_pos = first_pos-1;


      int total_neg = first_pos;

      for(int i=0;i<total_neg;i+=2){
          swap(arr,first_pos,last_neg);
          first_pos--;
          last_neg++;
          while((i+1)<first_pos){
                swap(arr,first_pos,first_pos-1);
                first_pos--;
          }
          first_pos = last_neg+1;
      }
    */

      //#4. Using 2-pointers and shifting elements using temp
        //#4.1: Find firstNeg and firstPos -> Swap -ve with +ve if +ve is ahead -> shift the +ve (behind one) from behindIndex to rignt of -ve -> Then move curr += 2 and repeat;;
        //# 4.2: We can even use odd and even index instead of ahead and behind
        int firstPos = -1;
        int firstNeg = -1;

        int curr = 0;

        while (curr < arr.length) {
            firstPos = -1;
            firstNeg = -1;

            //finding firstPositive
            for (int i = curr; i < arr.length; i++) {
                if (arr[i] >= 0) {
                    firstPos = i;
                    break;
                }
            }
            //finding firstNegative
            for (int i = curr; i < arr.length; i++) {
                if (arr[i] < 0) {
                    firstNeg = i;
                    break;
                }
            }

            if(firstNeg == -1 || firstPos == -1) break;

            //finding out if firstPos or FirstNeg is ahead
            int ahead = Math.min(firstPos, firstNeg);
            int behind = Math.max(firstPos, firstNeg);

            //j will be used for shifting after we swap behind elem next to ahead
            int j = ahead + 2;

            //storing temp to keep track of elements
            int temp = arr[ahead + 1];

            int temp2 = -1;

            if(ahead+2 < arr.length)
                temp2 = arr[ahead + 2];


            arr[ahead + 1] = arr[behind];

            //after swapping: if pos is behind neg then swap
            if(firstPos!=ahead) swap(arr,curr,ahead+1);


            //will be used for shifting elements after placing ahead with behind
            while (j <= behind && j+1< arr.length) {
                arr[j] = temp;
                temp = temp2;
                temp2 = arr[j + 1];
                j++;
            }
            if(j<arr.length) arr[j] = temp;

            curr+=2;
        }

    }

    public static ArrayList commonElem(int[] arr1, int[] arr2, int[] arr3){
        //#1. Use 3 sets and find common elements/ Use hashmap to store counts
        //#2. 3 pointers
        ArrayList<Integer> ans = new ArrayList<>();
        int i=0;
        int j=0;
        int k=0;
        while(i<arr1.length && j<arr2.length && k<arr3.length){
            if(arr1[i]==arr2[j] && arr2[j]==arr3[k]){
                ans.add(arr1[i]);
                i++;j++;k++;
            } else if(arr1[i]<arr2[j] && arr1[i]<arr3[k]){
                i++;
            } else if(arr2[j]<arr1[i] && arr2[j]<arr3[k]){
                j++;
            } else if(arr3[k]<arr2[j] && arr3[k]<arr1[i]){
                k++;
            }
            else if(arr1[i]>arr2[j] && arr1[i]>arr3[k]){
                j++;
                k++;
            }else if(arr2[j]>arr1[i] && arr2[j]>arr3[k]){
                k++;
                i++;
            }else{
                i++;
                j++;
            }
        }
        return ans;
    }
    public static int pairSum(int[] arr, int sum){
        //#1. Brute force
        //#2. Use Hashmap to store count
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            if (map.containsKey(sum-arr[i])){
                count += map.get(sum-arr[i]);
            }
            map.put(arr[i],map.containsKey(arr[i])?map.get(arr[i])+1:1);
        }
        return count;
    }

    public static int bestTimeStockSell(int[] arr){
        //#1. Brute force
        //#2. Find and store max after each iteration in a queue/stack (next greatest element)
        Stack<Integer> st= new Stack<>();
        int[] greatest = new int[arr.length];

        int i = arr.length-1;
        st.push(-1);
        while(i>=0){
            if(arr[i]>st.peek()) {
                st.push(arr[i]);
                greatest[i] = -1;
            }else{
                greatest[i] = st.peek();
            }
            i--;
        }

        int max = -1;
        for(int j=0;j<arr.length-1;j++){
            max = Math.max(max,greatest[j]-arr[j]); //could use this in first loop
        }

        return max;
        /* #2.Using one for loop
        int maxProfit = 0;
        int[] greatest = new int[arr.length];
        greatest[greatest.length-1] = -1;

        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]>arr[i+1] && arr[i]>greatest[i+1]){
                greatest[i] = -1;
            }else if(arr[i]<=greatest[i+1] ){
                greatest[i] = greatest[i+1];
            }else{
                greatest[i] = arr[i+1];
            }

            maxProfit = Math.max(maxProfit,(greatest[i]>=0)?greatest[i]-arr[i]:-1);
        }
        return maxProfit;
         */


        /* #3. Easier version
         int buy=Integer.MAX_VALUE,sell=0;
        for(int i=0;i<prices.length;i++){
            buy=Math.min(buy,prices[i]);
            sell=Math.max(sell,prices[i]-buy);
        }
       return sell;
         */
    }


    public static ArrayList<ArrayList<Integer>> mergeIntervals(int[][] arr){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        ArrayList<Integer> curr = new ArrayList<>();

        int i=0;
        int j = i+1;

        ans.add(curr);

        while(j<arr.length);
            if(arr[i][1]>=arr[j][0]){
                curr.add(arr[i][0]);
                curr.add(arr[j][1]);
                j++;
            }else{
                curr.add(arr[i][0]);
                curr.add(arr[i][1]);
                ans.add(curr);
                i++;
                j++;
            }
        return ans;
        }



    public static void mergeSorted(int[] arr1, int[] arr2){
        //#1. Merge using two pointers in new array
        //#2. Merging without using extra space
        int n = arr1.length;
        int m = arr2.length;
        int i=n-1,j=0;
        while(i>=0 && j<m){
            if(arr1[i]>arr2[j]){
                Twoswap(arr1,arr2,i,j);
                i--;
            }
            else j++;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

    }

    public static int duplicate(int[] arr){
        //#1. Brute force - nested for loops
        //#2. Sort them and compare the next element
        //#3. Using a set
        //#4. Using sum of array- sum of natural number
        //#5. bit XOR
        int ans = 0;
        for(int i=1;i<=arr.length-1;i++) ans=ans^i;
        for(int i=0;i<arr.length;i++){
            ans = ans^arr[i];
        }
        return ans;
    }

    public static int minimizeJumps(int[] arr){
        //#1. Brute force
        //#2 DP - O(n2)

        //#3 using a single for loop O(n)
        int maxReachable = 0,AfterJumpPosition=0, jumps = 0;
        if(arr.length==1) return 0;
        if(arr[0]==0) return -1;

        //using i ptr to find maxReachable for each index
        for(int i=0;i<arr.length-1;i++){

            maxReachable = Math.max(maxReachable,i+arr[i]);

            //if we reach maxReachable
            // and current position's element is zero, means that we can't move forward

            if(i==maxReachable && arr[i]==0) return -1;

            //if we reach actual position,update the position and inc. jump
            if(i==AfterJumpPosition){
                jumps++;
                AfterJumpPosition = maxReachable;
            }

        }

        return jumps;
    }


    public static int minimizeHeights(int[] arr, int k){

        int ans = arr[arr.length-1]-arr[0];
        int largest = arr[arr.length-1] - k;
        int smallest = arr[0] + k;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=1;i< arr.length-1;i++){
            max = Math.max(arr[i-1]+k,largest);
            min = Math.min(arr[i]-k,smallest);
            if(min<0) continue;
            ans = Math.min(ans,max-min);

        }
        return ans;

    }

    public static void rotate(int[] arr,int k){
        //#1. Use another array to store rotated array using res[(i+k)%arr.length] = arr[i]
        //#2. Reversing whole and part of arrays

        rec_reverse(arr,0,arr.length-1);
        rec_reverse(arr,0,k-1);
        rec_reverse(arr, k, arr.length-1);
    }

    public static void intersection(int[] arr1, int[] arr2){
        Map<Integer, Integer> map = new HashMap<>();

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i< arr1.length;i++){
            map.put(arr1[i],map.getOrDefault(arr1[i],0)+1);
        }

        for(int j=0;j< arr2.length;j++){
            if(map.containsKey(arr2[j]) && map.get(arr2[j])>0){
                arr.add(arr2[j]);
                map.put(arr2[j],map.get(arr2[j])-1);
            }
        }
        for(int i:arr){
            System.out.print(i+" ");
        }
    }

    public static ArrayList union(int[] arr, int[] arr2){
        //#1 Brute force using nested for loops
        //#2 sort them and use two pointers
        //#3 Using sets - store one array in set and use set to find common in arr2
        ArrayList<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i=0;i< arr.length;i++){
            ans.add(arr[i]);
            set.add(arr[i]);
        }
        for(int i=0;i< arr2.length;i++){
            if(set.contains(arr2[i])) set.remove(arr2[i]);
            else ans.add(arr2[i]);
        }
        return ans;
    }

    public static void moveNeg(int[] arr){
        int i = 0;
        int j = arr.length-1;

        while(i<=j){
            if(arr[j]<0 && arr[i]>=0){
                swap(arr, i, j);
                i++;
                j--;
            }else if(arr[i]<0 && arr[j]>=0) {
                i++;
                j--;
            }else if(arr[i]>=0 && arr[j]>=0){
                j--;
            }else i++;
        }
    }

    public static void sortZerosOnesTwos(int[] nums){
        //Method #1 - Count no. of 0s 1s and 2s and then fill the arr

        //Method #2 - Using pointers (imp)
        int left = 0;
        int right = nums.length - 1;
        int current = 0;
        while(current <= right) {
            if(nums[current] == 0){
                swap(nums, left, current);
                left++;
                current++;
            }
            else if(nums[current] == 1) {
                current++;
            }
            else {
                swap(nums, right, current);
                right--;
            }
        }
    }

    public static int kmax(int[] arr, int k){
        //Method#2 - Use priority queues or heap for (n-k)log(k)
        /*
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i< arr.length;i++){

            pq.add(arr[i]);

            if(pq.size()>3)
                pq.remove();
        }

        System.out.println(pq.remove());
        */
        //Method#3 - use quick sort partitioning ~~ Quick select algo or Lomuto Partition

        //M1: Bubble sort till we find kth largest element
        for(int i=0;i<k;i++){
         for(int j=0;j<arr.length-i-1;j++) {
             if(arr[j]>arr[j+1])
                swap(arr,j,j+1);
         }
        }
        return arr[arr.length-k];
    }


    //1. REVERSE an ARRAY

    public static void reverse(int[] arr){
        int i = 0;
        int j = arr.length-1;
        while(i<=j){
            swap(arr,i,j);
            i++;
            j--;
        }
    }

    public static void rec_reverse(int[] arr,int i, int j){
        if(i>=j) return;
        rec_reverse(arr,i+1,j-1);
        swap(arr,i,j);
    }

    // maxMin
    public static int[] maxMin(int[] arr){
        int[] ans = new int[2];
        int max = arr[0];
        int min = arr[0];
        for(int i=1;i< arr.length;i++){
            max = Math.max(arr[i],max);
            min = Math.min(arr[i],min);
        }
        ans[0] = max;
        ans[1] = min;
        return ans;
    }

    public static int[] rec_maxMin(int[] arr,int n, int[] ans){
        /*
        if(i==arr.length) return Integer.MAX_VALUE;
        int returnedMin = min(arr,i+1);
        return Math.min(returnedMin,arr[i]);
         */

        if(n==0){
            ans[0] = arr[0];
            ans[1] = arr[0];
            return ans;
        }

        rec_maxMin(arr,n-1,ans);

        ans[0] = Math.max(ans[0],arr[n]);
        ans[1] = Math.min(ans[1],arr[n]);

        return ans;
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
