import java.util.*;

public class StringPrac {
    public static void main(String[] args) {
        anagrams(new String[]{"act","god","cat","dog","tac"});
    }

    public static void anagrams(String[] arr){
        Map<String, ArrayList<String>> map = new HashMap<>();

        for(String i: arr){

            char[] c = i.toCharArray();
            Arrays.sort(c);
            ArrayList<String> ar;

            String sorted = String.valueOf(c);

            if(!map.containsKey(sorted)){
                ar = new ArrayList<>();
            }else{
                ar = map.get(sorted);
            }
            ar.add(i);
            map.put(sorted,ar);
        }
        System.out.println(map.toString());
    }

    public static String secFrequent(String[] arr, int N)
    {
        Map<String,Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            if(map.containsKey(arr[i]))
                map.put(arr[i],map.get(arr[i])+1);
            else map.put(arr[i],1);
            max = Math.max(map.get(arr[i]),max);
        }

        String secondMaxString = "";
        int secondMax = 0;
        for(String s:map.keySet()){
            if(secondMax < map.get(s) && map.get(s)<max){
                secondMax = map.get(s);
                secondMaxString = s;
            }
        }

        return secondMaxString;
    }

    public static void recFormSentence(String[][] arr, int i, String ans){
        if(i==arr.length){
            System.out.println(ans);
            return;
        }

        for(int j=0;j<arr[i].length;j++) {
            if(!arr[i][j].equals(""))
                recFormSentence(arr,i+1,ans+" "+arr[i][j]);
        }
    }

    public static void smallestWindowContainingAnotherString(String s1,String s2){
        Map<Character,Integer> map = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        if(s1.length()<s2.length()) {
            System.out.println(-1);
            return;
        }


        for(int i=0;i<s2.length();i++){
            map2.put(s2.charAt(i),map2.getOrDefault(s2.charAt(i),0)+1);
        }

        int i = 0;
        int j = 0;
        int count = 0;
        while(count!=s2.length() && j<s1.length()){
            if( map2.containsKey(s1.charAt(j))) {
                if (!map.containsKey(s1.charAt(j))) {
                    map.put(s1.charAt(j), 1);
                    count++;
                } else {
                    map.put(s1.charAt(j), map.get(s1.charAt(j)) + 1);
                }
            }
            j++;
        }

        if(count<s2.length()) {
            System.out.println(-1);
            return;
        }

        j--;

        int min = Integer.MAX_VALUE;
        while(j<s1.length()){

            while(!map2.containsKey(s1.charAt(i)) ||
                    map.containsKey(s1.charAt(i)) &&
                            map.get(s1.charAt(i)) > map2.get(s1.charAt(i)))
            {

                    if(map.containsKey(s1.charAt(i)) &&
                            map.get(s1.charAt(i)) > map2.get(s1.charAt(i)))
                        map.put(s1.charAt(i), map.get(s1.charAt(i)) - 1);
                    i++;
                }


            min = Math.min(j-i+1,min);
            j++;

            if(j<s1.length() && map.containsKey(s1.charAt(j))){
                map.put(s1.charAt(j),map.get(s1.charAt(j))+1);
            }

        }

        System.out.println(min);

    }

    public static int cyberCafeCustomers(String st,int maxCapacity){
        int[] alphabets = new int[26];

        int occupied = 0;
        int result = 0;
        for(int i=0;i<st.length();i++){
            int index = st.charAt(i)-'A';

            //entry of a customer
            if(alphabets[index]==0){
                if (occupied < maxCapacity) {
                    alphabets[index] = 1;
                    occupied++;
                } else {
                    alphabets[index] = -1;
                }
                //exit of a customer
            }else {
                if(alphabets[index]==1){
                    //occupied customer
                    occupied--;
                }else{
                    if(occupied==maxCapacity) result++; //waiting cust. left
                }
                alphabets[index] = 0;
            }

        }
        return result;
    }

    public static void KMPSearch(String txt, String pattern){
        int[] lps = new int[pattern.length()];
        computeLPS(pattern,lps);

        int i = 0;
        int j = 0;
       while (i<txt.length()){
           //if character matches, just increase the counter
           if(txt.charAt(i)==pattern.charAt(j)){
               i++;
               j++;
           }//if pattern matches, then print and then move the counter j to lps[j-1];
           if(j==pattern.length()){
               System.out.println(i-j);
               j = lps[j-1];
           }else if(i<txt.length() && txt.charAt(i)!=pattern.charAt(j)){   //if there's mismatch, move j to lps if j!=0 else move i
               if(j!=0)
                j = lps[j-1];
               else i++;

           }
       }
    }

    public static void computeLPS(String pattern,int[] ans){
        //calculates max length of prefix which is also suffix for each length of pattern's substring: lps[i] represents max length of prefix and suffix from substring 0->i
        ans[0] = 0;
        int i = 1;
        int back = 0;

        while(i<pattern.length()){
            if(pattern.charAt(i)==pattern.charAt(back)){
                ans[i] = ++back;
                i++;
            }else{
                if(back!=0){
                    back = ans[back-1];
                }else{
                    ans[i] = 0;
                    i++;
                }
            }
        }
    }

    public static boolean isIsomorphic(String str1, String str2){
        if(str1.length()!=str2.length()) return false;
        Map<Character,Character> map1 = new HashMap<>();


        for(int i=0;i<str1.length();i++){
            if(map1.containsKey(str1.charAt(i))){
                if(str2.charAt(i)!= map1.get(str1.charAt(i))) return false;
            }else{
                if(map1.containsValue(str2.charAt(i))) return false;
                map1.put(str1.charAt(i), str2.charAt(i));
            }
        }

        return true;
    }

    public static int smallestDistinctWindow(String str){
        //Variable sliding window
        Map<Character,Integer> map = new HashMap<>(); //will keep the count of characters in the window
        for(int i=0;i<str.length();i++){
            map.put(str.charAt(i),0);
        }
        int i=0;
        int j=0;
        int count = 0;
        int min = Integer.MAX_VALUE;

        while(j<str.length()){
            if(map.get(str.charAt(i))==0) count++;

            map.put(str.charAt(j),map.get(str.charAt(i))+1);

            //if current window contains each alphabet present in map, we will try to decrease window size from behind while updating min length
            if(count==map.size()){
                while(i<str.length() && map.get(str.charAt(i))>1){
                    map.put(str.charAt(j),map.get(str.charAt(i))-1);
                    i++;
                }
                if(min>j-i+1){
                    min = j-i+1;
                }
            }
            j++;
        }
        return min;
    }

    public static void longestPrefixSuffix(String str){
        //2. LPS Array - implemented above

        /*1. Brute force
        int max = -1;
        for(int i=0;i<str.length();i++){
            int j = 0;
            int k = str.length()-i;

            int count = 0;
            while(j!=str.length()-1 && j<i){
                if(str.charAt(j)!=str.charAt(k)){
                    count = 0; //if k doesn't reach the end of string
                    break;
                }
                count++;
                j++;
                k++;
            }
            max = Math.max(max,count);
        }
        return max;

         */
    }

    public static String longestCommonPrefix(String[] str){
        if(str.length==1) return str[0];

        String common = str[0];
        for(int i=1;i<str.length;i++){
            int j = 0;

            while(j<common.length() && j<str[i].length()){
                if(common.charAt(j)!=str[i].charAt(j)){

                    break;
                }
                j++;
            }
            common = str[i].substring(0,j);
            if(common.equals("")) return "";
        }

        return common;

    }

    public static String longestPalindromicSubstring(String str){
        //1. Generate all substrings and check for palindrome
        //2. DP
        //3. Expand palindrome string using 3 pointers -> start, middle and end

        String ans = "";

        //k will act as middle pointer around which str will expand
        for(int k=0;k<str.length();k++){
            int length1 = expandFromMiddle(str,k,k);        //in case of even length palindrome
            int length2 = expandFromMiddle(str,k,k+1);    // in case of odd length palindrome

            int max = Math.max(length1,length2);

            int start = k - (max-1)/2;  //finding starting index of longest palindrome using k pointer
            int end = k + (max)/2;      //finding end index of longest palindrome using k pointer

            ans = str.substring(start,end+1);  //or we can just declare start and end outside loop
                                               // and return using start and end
        }
        return ans;
    }

    public static int expandFromMiddle(String str, int i,int j){
        int start = i;
        int end = j;

        //if matching characters are found we expand left and right
        while(start>=0 && end<str.length() && str.charAt(start)==str.charAt(end)){
            start--;
            end++;
        }

        return end-start-1; //returns length of longest palindromic substring found
    }


    public static void equalBinaryString(String str){
        int count0 = 0;
        int count1 = 0;

        int start = 0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='0') count0++;
            if(str.charAt(i)=='1') count1++;

            if (count0 == count1) {
                System.out.print(str.substring(start,i+1)+" ");
                start = i+1;
            }
        }
    }

    public static int minNumberOfFlips(String str){
        int firstAlt = 0;
        int secondAlt = 0;

        for(int i=0;i<str.length();i++) {
            if (i % 2 == 0) {
                if (str.charAt(i) == '0') firstAlt++;
                else secondAlt++;
            } else {
                if (str.charAt(i) == '0') secondAlt++;
                else firstAlt++;
            }
        }

        return Math.min(secondAlt,firstAlt);


    }

    public static void permutations(String str, String ans,int l){
        // Using rec
        /*
                  abc,""
               /     |    \
              a      b     c
             / |    / |    | \
            ab ac  ba bc   ca cb
            |   |   |  |   |   |
           abc acb bac bca cab cba

         */
        if(ans.length()==l){
            System.out.println(ans);
            return;
        }
        for(int i=0;i<str.length();i++){
            permutations(str.substring(0,i)+str.substring(i+1),ans+str.charAt(i),l);

        }
    }

    public static void romanToDec(String str){
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int num = 0;
        for(int i=0;i<str.length();i++){
            if(i<str.length()-1 && (map.get(str.charAt(i+1))>map.get(str.charAt(i)))){
                num += map.get(str.charAt(i+1)) - map.get(str.charAt(i));
                i++;
            }else num+=map.get(str.charAt(i));
        }
        System.out.println(num);
    }

    public static void subSequences(String str,String ans){
        //1. Rec - pick/don't pick
        if(str.length()==0){
            if(!ans.equals(""))
                System.out.print(ans+" ");
            return;
        }
        subSequences(str.substring(1),ans+str.charAt(0));
        subSequences(str.substring(1),ans);
    }

    public static void genSubstrings(String s, String ans){
        if(!ans.equals(""))  System.out.println(ans);

        for(int i=0;i<s.length();i++){
            genSubstrings(s.substring(i+1),ans+s.charAt(i));
        }
    }

    public static boolean isRotated(String s1, String s2){
        //#1. Rotate string1 for length number of times and match it with s2;
        //#2. Concat and use contains method
        return s1.concat(s1).contains(s2);
    }
    public static ArrayList<Character> duplicate(String s){
        //#1. Using nested for loops
        //#2. Sort characters and using a loop to compare next character
        //#3. Set or HashMap or CountArray
        ArrayList<Character> ch = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for(char i: s.toCharArray()){
            if(set.contains(i) && !ch.contains(i)) ch.add(i);
            set.add(i);
        }
        return ch;
    }

    public static boolean rec_palindrome(String s){
        if(s.length()==1) return true;
        if(s.length()==2) return s.charAt(0)==s.charAt(1);
        return rec_palindrome(s.substring(1,s.length()-1)) && (s.charAt(0)==s.charAt(s.length()-1));
    }

    public static String rec_reverse(String s){
        if(s.length()==1)
            return s;

        return rec_reverse(s.substring(1)) + s.charAt(0);
    }
    public static String reverse(String s){
        String rev = "";
        int j=s.length()-1;
        while(j>=0){
            rev+=s.charAt(j);
            j--;
        }
        return rev;
    }

    public static String rotateString(String str,int k){
        char[] rev = new char[str.length()];
        for(int i=0;i< rev.length;i++){
            rev[(i+k)% rev.length] = str.charAt(i);
        }

        return String.valueOf(rev);
    }

    public static String swap(String str, int i, int j)
    {
        char[] ch = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }

    public static String removeConsecutiveChar(String str,int n){
        if(n==0) return str;
        if(str.charAt(n-1)==str.charAt(n))
            return removeConsecutiveChar(str.substring(0,n)+str.substring(n+1),n-1);

        return removeConsecutiveChar(str,n-1);
    }
}
