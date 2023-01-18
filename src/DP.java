import java.util.Arrays;

public class DP {
    public static void main(String[] args) {

    }

    public static int O_lKnapsack(int[] wt, int[] val,int capacity,int index){
        if(index==0){
            if(capacity-wt[0]>=0){
                return val[0];
            }else return 0;
        }
        int incl = 0;

        if(capacity-wt[index]>=0)
            incl = O_lKnapsack(wt,val,capacity-wt[index],index-1)+val[index];

        int excl = O_lKnapsack(wt,val,capacity,index-1);

        return Math.max(incl,excl);
    }

    public static int O_lKnapsackDP(int[] wt, int[] val,int capacity,int index,int[] dp){
        if(index==0){
            if(capacity-wt[0]>=0){
                return val[0];
            }else return 0;
        }

        if(dp[index]!=-1) return dp[index];

        int incl = 0;

        if(capacity-wt[index]>=0)
            incl = O_lKnapsackDP(wt,val,capacity-wt[index],index-1,dp)+val[index];

        int excl = O_lKnapsackDP(wt,val,capacity,index-1,dp);

        dp[index] = Math.max(incl,excl);
        return dp[index];
    }

    public static int O_lKnapsackTab(int[] wt, int[] val,int capacity){
       int[][] dp = new int[wt.length][capacity+1];

       for(int i=0;i<=capacity;i++) {
           if (capacity - wt[0] >= 0)
               dp[0][i] = val[0];
           else dp[0][i] = 0;
       }


       for(int i=1;i<= wt.length;i++){
            for(int w=0;w<=capacity;w++){
                int incl = 0;

                if(w-wt[i]>=0)
                    incl = dp[i-1][w-wt[i]]+val[i];

                int excl = dp[i-1][w];

                dp[i][w] = Math.max(incl,excl);
            }
       }
       return dp[wt.length-1][capacity];

    }

    public static int minCostClimbingStairs(int[] cost){
        int n = cost.length; //n will be no. of stairs
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);

        return Math.min(minCostClimbingStairs(cost,n-1,dp),
                minCostClimbingStairs(cost,n-2,dp));
        //we are returning the min of n-1 and n-2 stairs as there's no cost of last stair
    }

    private static int minCostClimbingStairs(int[] cost,int n,int[] dp){
        if(n==0) return cost[0];
        if(n==1) return cost[1];
        if(dp[n]!=-1) return dp[n];


        dp[n] = cost[n] + Math.min(minCostClimbingStairs(cost,n-1,dp),
                           minCostClimbingStairs(cost,n-2,dp));
        return dp[n];
    }
/*
    private static int minCostClimbingStairsTabulation(int[] cost,int n){
        int[] dp = new int[n+1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        //instead of using dp array,
        // we can use two ptrs to keep track of last two elements
        for(int i=2;i<n;i++){
            dp[i] = cost[i]+Math.min(dp[i-1],dp[i-2]);
        }
        return Math.min(dp[n-1],dp[n-2]);
    }
 */

    public static int minNumberOfCoins(int[] num, int n){
        if(n==0) return 0;
        if(n<0) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        for(int i=0;i<num.length;i++){
            int ans = Math.min(min,minNumberOfCoins(num,n-num[i]));

            if(ans!=Integer.MAX_VALUE){
                min = Math.min(min,ans+1); //adding 1 coin while returning
            }
        }
        return min;
    }
    /*
    public static int minNumberOfCoinsDP(int[] num, int n,int[] dp){
        if(n==0) return 0;
        if(n<0) return Integer.MAX_VALUE;

        if(dp[n]!=-1) return dp[n];

        int min = Integer.MAX_VALUE;
        for(int i=0;i<num.length;i++){
            int ans = Math.min(min,minNumberOfCoinsDP(num,n-num[i],dp));

            if(ans!=Integer.MAX_VALUE){
                min = Math.min(min,ans+1); //adding 1 coin while returning
            }
        }
        dp[n] = min;
        return dp[n];
    }

    public static int minNumberOfCoinsTab(int[] num, int n){
      int[] dp = new int[n+1];
      Arrays.fill(dp,Integer.MAX_VALUE);

      dp[0] = 0;

      //dp[i] will represent min. no. of coins required to make i
      for(int i=1;i<=n;i++){
          //inner for loop will get ans for dp[i]
          for(int j=0;j<num.length;j++){
              if(i-num[j]>=0 && dp[i-num[j]]!=Integer.MAX_VALUE){
                  dp[i] = Math.min(dp[i],dp[i-num[j]]+1);
              }
          }
      }
      return dp[n];
    }
     */

    public static int maxSumNonAdjacent(int[] arr,int n){
        if(n==0) return arr[0];
        if(n<0) return 0;

        int incl = maxSumNonAdjacent(arr,n-2)+arr[n];
        int excl = maxSumNonAdjacent(arr, n - 1);

        return Math.max(incl,excl);
    }
  /*
    public static int maxSumNonAdjacentDP(int[] arr,int n,int[] dp){
        if(n==0) return arr[0];
        if(n<0) return 0;

        if(dp[n]!=-1) return dp[n];

        int incl = maxSumNonAdjacentDP(arr,n-2,dp)+arr[n];
        int excl = maxSumNonAdjacentDP(arr, n - 1,dp);

        dp[n] = Math.max(incl,excl);
        return dp[n];
    }

    public static int maxSumNonAdjacentTab(int[] arr,int n){
       int[] dp = new int[n];
       dp[0] = arr[0];
       dp[1] = Math.max(arr[1],arr[0]); //in case there are only 2 elems in arr

       for(int i=2;i<n;i++){
           dp[i] = Math.max(dp[i-1],dp[i-2]+arr[i]);
       }
       return dp[n-1];
    }
    */

    public int cutSegments(int n,int x,int y, int z){
        if(n==0) return 0;
        if(n<0) return Integer.MIN_VALUE;

        //adding 1 to get no. of segments
        int ans1 = cutSegments(n-x,x,y,z)+1;
        int ans2 = cutSegments(n-y,x,y,z)+1;
        int ans3 = cutSegments(n-z,x,y,z)+1;

        return Math.max(ans1,Math.max(ans2,ans3));
    }
    /*
    public int cutSegmentsDP(int n,int x,int y, int z,int[] dp){
        if(n==0) return 0;
        if(n<0) return Integer.MIN_VALUE;

        if(dp[n]!=-1) return dp[n];

        int ans1 = cutSegmentsDP(n-x,x,y,z,dp)+1;
        int ans2 = cutSegmentsDP(n-y,x,y,z,dp)+1;
        int ans3 = cutSegmentsDP(n-z,x,y,z,dp)+1;

        dp[n] =  Math.max(ans1,Math.max(ans2,ans3));
        return dp[n];
    }

    public int cutSegmentsTab(int n,int x,int y, int z){
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MIN_VALUE);

        dp[0] = 0;

        for(int i=1;i<=n;i++){
            int ans1 = dp[i-x]+1; //check n-x>=0
            int ans2 = dp[i-y]+1; //check n-y>=0
            int ans3 = dp[i-z]+1; //check n-z>=0

            dp[i] =  Math.max(ans1,Math.max(ans2,ans3));
        }
        return dp[n];
    }
     */


}
