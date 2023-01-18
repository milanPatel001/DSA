import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;



public class Greedy {
    public static void main(String[] args) {

    }

    public static int maxSumDifference(int[] arr,int n){
        Arrays.sort(arr);
        int i = 0, j = n - 1, sum = 0;
        boolean off = true;
        while(i < j) {
            sum += Math.abs(arr[i] - arr[j]);
            if(off) {
                i++;
            } else {
                j--;
            }
            off = !off;
        }
        sum += Math.abs(arr[0] - arr[n/2]); //remaining difference
        return sum;
    }

    public static long maxSumAfterKNegations(int[] a,int k,int n){
        Arrays.sort(a);
        long sum = 0;

        int i=0;
        while(k>0 && i<n){
            long ans = a[i];
            if((a[i]>0) || (a[i]<0 && i==n-1) || (i+1<n && a[i]<0 && a[i+1]>0) ){
                ans *= Math.pow(-1,k);
                sum+=ans;
                i++;
                break;
            }

            sum+=ans*-1;
            i++;
            k--;
        }

        while(i<n) {
            sum+=a[i];
            i++;
        }
        return sum;
    }

    public static int maxSubsetProduct(int n, int[] arr){
        //if neg are odd, divide the product by biggest negative number, ignore zeros.
            int neg = 0;
            int zeros = 0;
            int maxNegativeNum = Integer.MIN_VALUE;
            long product = 1;

            long mod = 1000000000+7;
            if(n==1) return arr[0];

            for(int i=0;i<n;i++){
                if(arr[i]<0) neg++;
                if(arr[i]==0) {
                    zeros++;
                    continue;
                }
                if(arr[i]<0){
                    maxNegativeNum = Math.max(arr[i],maxNegativeNum);
                }
                product= (product *arr[i])%mod;

            }

            if(zeros==n) return 0;
            if(neg==1 && neg+zeros==n) return 0;
            if(neg%2==0) return (int)product;
            return (int)(product/maxNegativeNum);

    }

    public static int maxStocksOniTHDay(int[] stocks,int n,int money){
        Pair[] arr = new Pair[n];

        // Making pair of product cost and number of day..
        for (int i = 0; i < n; i++)
            arr[i] = new Pair(stocks[i], i + 1);

        // Sorting the pair array.
        Arrays.sort(arr, Comparator.comparingInt(a-> (int) a.second));
        // Calculating the maximum number of stock
        // count.
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int numOfCoinsThatCanBePurchased = Math.min((int)arr[i].second, money / (int)arr[i].first);

            ans += numOfCoinsThatCanBePurchased;

            money -= (int)arr[i].first * numOfCoinsThatCanBePurchased;
        }
        return ans;
    }

    public static int ActivitySelectionProblem(int[] start, int[] end,int n){
            int maxNumberOfMeetings = 0;
            Pair2[] pair = new Pair2[n];
            for(int i=0;i<n;i++){
                pair[i] = new Pair2(start[i],end[i]);
            }

            Arrays.sort(pair,Comparator.comparingInt(a->a.dep));

            int prevEnd = pair[0].dep;
            for(int i=1;i<n;i++){
                if(pair[i].arr>prevEnd){
                    maxNumberOfMeetings++;
                    prevEnd = pair[i].dep;
                }
            }
            return maxNumberOfMeetings;
    }

    public static int findPlatform(int arr[], int dep[], int n)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Pair2[] time = new Pair2[n];
        for(int i=0;i<n;i++){
            time[i] = new Pair2(arr[i],dep[i]);
        }

        Arrays.sort(time, Comparator.comparingInt(a -> a.arr));


        pq.add(time[0].dep);

        for(int i=1;i<n;i++){
            if(time[i].arr>pq.peek()){
                pq.poll();
                pq.add(time[i].dep);
            }
            else {
                pq.add(time[i].dep);
            }

        }
        return pq.size();

    }

    public static int maxStoppedTrains(Pair3[] trainInfo, int n) {
        Arrays.sort(trainInfo, Comparator.comparingInt(a -> a.depTime));
        int[] platform = new int[n + 1];
        int maxStoppedTrains = 0;
        for (int i = 0; i < trainInfo.length; i++) {
            int p = trainInfo[i].platform;
            if (platform[p] == 0 || platform[p] <= trainInfo[i].arrTime) {
                maxStoppedTrains++;
                platform[p] = trainInfo[i].depTime;
            }
        }
        return maxStoppedTrains;
    }
}

class Pair3 {
    int arrTime;
    int depTime;
    int platform;

    public Pair3(int a, int d, int p) {
        arrTime = a;
        depTime = d;
        platform = p;
    }
}

class Pair2 {
    int arr;
    int dep;

    public Pair2(int a, int d) {
        arr = a;
        dep = d;
    }
}