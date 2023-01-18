import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaPrac {

    public static class Pair {
        int a;
        int b;
        int c;

        public Pair(int d, int e){
            a = d;
            b = e;
        }

        public Pair(int d, int e, int f){
            a = d;
            b = e;
            c = f;
        }

        @Override
        public String toString() {
            return "{a=" + a +
                    ", b=" + b +
                    '}';
        }

    }

    public static void main(String[] args) {
        Pair[][] adj = {
                {},
                {},
                {},
        };


    }


}

/*
//1. Number of Islands - Graph
 void (){
     List<ArrayList<Integer>> adj = new ArrayList<>();

       int[][] grid = {
               {0,1,1,0},
               {0,1,1,0},
               {0,0,1,0},
               {0,0,0,0},
               {1,1,0,1}
       };

       Queue<Pair> q = new ArrayDeque<>();
       int[][] visited = new int[5][4];
       int cnt = 0;

       for(int i=0;i< grid.length;i++){
            for(int j=0;j< grid[i].length;j++){
                if(grid[i][j]==1 && visited[i][j]==0){
                    q.add(new Pair(i,j));
                    visited[i][j] = 1;
                    cnt++;
                    while(!q.isEmpty()){
                        Pair p = q.poll();
                        int r1 = p.a;
                        int c1 = p.b;

                        int[] delRow = {1,1,0,-1,-1,0,1,-1};
                        int[] delCol = {1,0,1,-1,0,-1,-1,1};

                        for(int k=0;k<8;k++){
                            int r = r1+delRow[k];
                            int c = c1+delCol[k];
                            if(r>=0 && r< grid.length && c>=0 && c< grid[i].length){
                                if(grid[r][c]==1 && visited[r][c]==0){
                                    visited[r][c] = 1;
                                    q.add(new Pair(r,c));
                                }
                            }
                        }

                    }
                }
            }
       }
 }

 //2. Flood fill
 public static void dfs(int[][] grid, int initColor, int i, int j, int newColor){
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};

        grid[i][j] = newColor;

        for(int k=0;k<4;k++){
            int nrow = i+delRow[k];
            int ncol = j+delCol[k];

            if(nrow>=0 && nrow< grid.length && ncol>=0 && ncol<grid[i].length) {
                if(grid[nrow][ncol]==initColor) {
                    dfs(grid, initColor, nrow, ncol, newColor);
                }
            }
        }
    }

    // 3.Rotten Oranges
       public static void rotten(){
        int[][] grid = {
                {0, 1, 2},
                {0, 1, 1},
                {2, 1, 1},
        };

        Queue<Pair> q = new ArrayDeque<>();
        int freshOranges = 0;
        for(int i=0;i< grid.length;i++){
            for(int j=0;j< grid[i].length;j++){
                if(grid[i][j]==1) freshOranges++;

                if(grid[i][j]==2){
                    q.add(new Pair(i,j,0));
                }
            }
        }

        int maxTime = 0;
        while(!q.isEmpty()){
            Pair p = q.poll();
            int r = p.a;
            int c = p.b;
            int t = p.c;

            maxTime = Math.max(maxTime,t);

            int[] delRow = {-1,0,1,0};
            int[] delCol = {0,1,0,-1};

            for(int k=0;k<4;k++){
                int nrow = r+delRow[k];
                int ncol = c+delCol[k];

               if(nrow>=0 && nrow<grid.length && ncol>=0 && ncol<grid[r].length) {
                   if (grid[nrow][ncol] == 1) {
                       grid[nrow][ncol] = 2;
                       freshOranges--;
                       q.add(new Pair(nrow, ncol, t + 1));
                   }
               }
            }
        }

        if(freshOranges!=0) System.out.println(-1);
        else System.out.println(maxTime);
     }

     //4. Undirected Cycle detection
       //a. using BFS

         int[] visited = new int[adjMatrix.length];

            Queue<Pair> q = new ArrayDeque<>();
            q.add(new Pair(1,-1));

            while(!q.isEmpty()){
                Pair p = q.poll();
                int parent = p.b;
                int node = p.a;

                for(int i:adjMatrix[node]){
                    if(visited[node]!=1){
                        visited[node] = 1;
                        q.add(new Pair(i,node));
                    }else if(parent!=i){
                        System.out.println(true); //return true
                    }
                }
            }
            //return false

         //b. using DFS
      public static boolean cycleDFS(int node,int parent,int[] visited, int[][] mat){
        visited[node] = 1;

        for(int i:mat[node]){
            if(visited[node]!=1){
                if(cycleDFS(i,node,visited,mat)) return true;
            }else  if(i!=parent) return true;
        }
        return false;
    }

//5. Nearest cell having 1
int[][] grid = {
                {0,0,0},
                {0,0,0},
                {0,0,1},
        };

        int[][] visited = new int[grid.length][grid[0].length];

        Queue<Pair> q = new ArrayDeque<>();

        for(int i=0;i< grid.length;i++){
            for(int j=0;j< grid[i].length;j++){
                if(grid[i][j]==1){
                    grid[i][j] = 0;
                    visited[i][j] = 1;
                    q.add(new Pair(i,j,0));
                }
            }
        }

        while (!q.isEmpty()){
            Pair p = q.poll();
            int r = p.a;
            int c = p.b;
            int step = p.c;

            int[] delRow = {-1,0,1,0};
            int[] delCol = {0,1,0,-1};


            for(int k=0;k<4;k++){
                int nrow = r+delRow[k];
                int ncol = c+delCol[k];

                if(nrow>=0 && nrow< grid.length && ncol>=0 && ncol<grid[r].length) {
                  if(visited[nrow][ncol]!=1){
                     visited[nrow][ncol]=1;
                     grid[nrow][ncol] = step+1;
                     q.add(new Pair(nrow,ncol,step+1));
                  }
                }
            }

        }

        for(int x=0;x< grid.length;x++){
            for(int y=0;y< grid[x].length;y++){
                System.out.print(grid[x][y]+" ");
            }
            System.out.println();
        }

//6. Replace O with X if O is surrounded by X
 (Hint: Boundary O's can never be surrounded)
 main(){
   char[][] grid = {
                {'X','X','X','X'},
                {'X','O','X','X'},
                {'X','O','O','X'},
                {'X','O','X','X'},
                {'X','X','O','O'},
        };

        int[][] visited = new int[grid.length][grid[0].length];

        for(int i=0;i< grid.length;i++){
            if(visited[i][0]!=1 && grid[i][0]=='O') dfs(grid,visited,i,0);
        }

        for(int i=0;i< grid.length;i++){
            if(visited[i][grid[0].length-1]!=1 && grid[i][grid[0].length-1]=='O') dfs(grid,visited,i, grid[0].length-1);
        }

        for(int j=0;j< grid[0].length;j++){
            if(visited[0][j]!=1 && grid[0][j]=='O') dfs(grid,visited,0,j);
        }

        for(int j=0;j< grid[0].length;j++){
            if(visited[grid.length-1][j]!=1 && grid[grid.length-1][j]=='O') dfs(grid,visited, grid.length-1, j);
        }

        for (int i=0;i<grid.length;i++){
            for(int j=0;j< grid[0].length;j++){
                if(visited[i][j]!=1) grid[i][j] = 'X';
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }

}
    public static void dfs(char[][] grid,int[][] visited,int i, int j){
        visited[i][j] = 1;

        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};

        for(int k=0;k<4;k++) {
            int nrow = i + delRow[k];
            int ncol = j + delCol[k];

            if(nrow>=0 && nrow<grid.length && ncol>=0 && ncol<grid[0].length) {
                if (grid[nrow][ncol] == 'O' && visited[nrow][ncol] != 1) {
                    dfs(grid, visited, nrow, ncol);
                }
            }

        }
    }

    //7. Number of Distinct Islands
      int[][] grid = {
                    {1,1,0,1,1},
                    {1,0,0,0,0},
                    {0,0,0,0,0},
                    {1,1,0,1,1},
                    {0,0,0,1,0}
            };

            Queue<Pair> q = new ArrayDeque<>();
            int[][] visited = new int[grid.length][grid[0].length];
            HashSet<ArrayList<String>> set = new HashSet<>();

            for(int i=0;i< grid.length;i++){
                for(int j=0;j< grid[0].length;j++){
                    if(grid[i][j]==1 && visited[i][j]!=1){
                        int baseR = i;
                        int baseC = j;

                        q.add(new Pair(i,j));
                        ArrayList<String> arr = new ArrayList<>();

                        arr.add("00");

                       // set.add(arr);

                        visited[i][j] = 1;

                        while(!q.isEmpty()){
                            Pair p = q.poll();
                            int r = p.a;
                            int c = p.b;

                            int[] delRow = {-1,0,1,0};
                            int[] delCol = {0,1,0,-1};

                            for(int k=0;k<4;k++){
                                int nrow = r+delRow[k];
                                int ncol = c+delCol[k];

                                if(nrow>=0 && nrow< grid.length && ncol>=0 && ncol< grid[0].length){
                                    if(grid[nrow][ncol]==1 && visited[nrow][ncol]!=1){
                                        visited[nrow][ncol]=1;
                                        q.add(new Pair(nrow,ncol));
                                        String s = (nrow-baseR)+""+(ncol-baseC);
                                        arr.add(s);
                                    }
                                }
                            }

                        }
                        set.add(arr);
                    }
                }
            }

        System.out.println(set);
        System.out.println(set.size());
`
`//8. Bipartite graph
      (Fact: Linear Graph (no cycle) and graph with even length cycle are bipartite ; graph w/ odd length cycle is not bipartite)
      //a. Using BFS

        int[] colored = new int[adj.length+1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        colored[1] = 1;
       //0->no color ; 1 and 2 -> color
        while(!q.isEmpty()){
            int node = q.poll();

            for(int i:adj[node]){
                if(colored[i]==0){
                    colored[i] = colored[node]==1?2:1;
                    q.add(i);
                }else if(colored[i]==colored[node]){
                    System.out.println(false); //return false
                }
            }
        }
        System.out.println(true);

        //b. Using DFS
            public static boolean isBipartiteDFS(int[][] adj, int[] colored, int node, int color){
        colored[node] = color;

        for(int i:adj[node]){
            if(colored[i]==0){
                int newColor = color==1?2:1;
                if(!isBipartiteDFS(adj,colored,i,newColor)) return false;
            }else if(colored[i]==color) return false;
        }
        return true;
    }

//9. Cycle Detection Directed Graph
    (Hint: Using path visited instead of parent)
    public static boolean isCycleDFSDirected(int node, int[][] adj, int[] visited, int[] pathVisited){
            visited[node] = 1;

            pathVisited[node] = 1;

            for(int i: adj[node]){
                if(i==-1) continue;

                if(visited[i]==0){
                    if(isCycleDFSDirected(i,adj,visited,pathVisited)) return true;
                }else if(pathVisited[i]==1) return true;
            }

            pathVisited[node] = 0;
            return false;

    }

//10. Eventual safe states (nodes that are not connected to cycle)
public static ArrayList<Integer> eventualSafeStates(int[][] adj){
        ArrayList<Integer> ans = new ArrayList<>();
        int[] visited = new int[adj.length];
        int[] pathVisited = new int[adj.length];

        int[] check = new int[adj.length];

        //check for every component
        for(int i=0;i< adj.length;i++){
            if(visited[i]==0){
                dfsCycleCheck(i,adj,visited,pathVisited,check);
            }
        }


        for(int i=0;i< check.length;i++){
            if(check[i]==1) ans.add(i);
        }
        return ans;
    }

    private static boolean dfsCycleCheck(int node, int[][] adj, int[] visited, int[] pathVisited, int[] check){
        visited[node] = 1;

        pathVisited[node] = 1;

        check[node] = 0;

        for(int i: adj[node]){
            if(i==-1) continue;

            if(visited[i]==0){
                if(dfsCycleCheck(i,adj,visited,pathVisited, check)){
                    check[node] = 0;  //cycle found in path
                    return true;
                };
            }else if(pathVisited[i]==1){
                check[node] = 0; //cycle found in path
                return true;
            }
        }

        check[node] = 1; //no cycle found in path
        pathVisited[node] = 0;
        return false;
    }

    //11. Topological sort
      //a. Using DFS
      public static void topoSortDFS(int node,int[][] adj, int[] visited, Stack<Integer> st){
            visited[node] = 1;


            for(int i:adj[node]){
                if(i==-1) continue;
                if(visited[i]==0){
                    topoSortDFS(i,adj,visited,st);
                }
            }
        st.push(node);
    }

    //b. Using BFS (Using Kahn's Algo) - Using indegree array
           int[][] adj = {
                {-1},
                {-1},
                {3},
                {1},
                {1,0},
                {0,2}

        };

        int[] visited = new int[adj.length];
       Queue<Integer> q = new ArrayDeque<>();
       int[] indegree = new int[adj.length];

       for(int i=0;i< adj.length;i++){
           for(int j=0;j< adj[i].length;j++){
               if(adj[i][j]!=-1) indegree[adj[i][j]]++;
           }
       }

       for(int k=0;k< indegree.length;k++){
           if(indegree[k]==0) q.add(k);
       }

       while(!q.isEmpty()){
           int node = q.poll();

           System.out.print(node+" ");
           for(int i:adj[node]){
               if(i==-1) continue;
               indegree[i]--;
            if(indegree[i]==0) q.add(i);
           }
       }

  // 12. Cycle Detection BFS using Kahn Algo
    --same as topo sort, just check the final size of topo sort arr or indegree remaining
    if topo arr size < adj.length then cycle found; or indegree rem means there a cycle

 // 13. Prerequisite tasks / Course Schedule
    Given pairs of (prereq, req), return true if all tasks can be done or find the order
    --- Use DFS Cycle Check or Kahn's Algo to find Cycle

  // 14. Eventual Safe States using BFS (Kahn's Algo)
       --- First, reverse all the edges to make the terminal nodes' indegree 0
       --- same as topo sort BFS

  // 15. Alien Dictionary
    String[] dict = {"baa","abcd","abca","cab","cad"};

        // consider abcd as 0123 node
        // Creating Directed graph using  ordering
       ArrayList<Integer>[] arr = new ArrayList[4];

       for(int i=0;i< dict.length-1;i++){
            int j=0;
            int k=0;

           String first = dict[i];
           String second = dict[i+1];

           while(j<first.length() && k<second.length()){
                if(first.charAt(j)==second.charAt(k)){
                    j++;
                    k++;
                }else{
                    if(arr[first.charAt(j)-'a']==null){
                        ArrayList<Integer> a = new ArrayList<>();
                        arr[first.charAt(j)-'a'] = a;
                    }
                    arr[first.charAt(j)-'a'].add(second.charAt(k)-'a');
                    break;
                }
            }

       }

       // and just topo sort the new arr (also check for cyclic dependency)

// 16. Shortest Path in DAG (Hint: Find toposort first)
         Pair[][] adj = {
                {new Pair(1,2), new Pair(4,1)},
                {new Pair(2,3)},
                {new Pair(3,1)},
                {},
                {new Pair(1,3),new Pair(3,4)}
        };

        Stack<Integer> st = new Stack<>();
        int[] visited = new int[adj.length];

        topoSortDFS(0,adj,visited,st);

        int[] dist = new int[visited.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[st.peek()] = 0;

        while(!st.isEmpty()){
            int node = st.pop();

            for(Pair i: adj[node]){
                dist[i.a] = Math.min(dist[i.a], dist[node]+i.b);
            }
        }

        System.out.println(Arrays.toString(dist));


    }

    public static void topoSortDFS(int node,Pair[][] adj, int[] visited, Stack<Integer> st){
        visited[node] = 1;


        for(Pair i:adj[node]){
            //a->neighbor, b->edge weight
            if(i.a == -1) continue;
            if(visited[i.a]==0){
                topoSortDFS(i.a,adj,visited,st);
            }
        }
        st.push(node);
    }

   // 17. Shortest path in Undirected Graph with unit weights
    int[][] adj = {
                {1,3},
                {0,2,3},
                {1,6},
                {0,4},
                {3,5},
                {4,6},
                {2,5,7,8},
                {6,8},
                {6,7},
        };

        Queue<Integer> q = new ArrayDeque<>();
        int[] dist =  new int[adj.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;

        int[] visited = new int[adj.length];

        q.add(0); //src node
        visited[0] = 1;

        while(!q.isEmpty()){
            int node = q.poll();

            for(int i:adj[node]){
                if(visited[i]==0){
                    visited[i] = 1;
                    q.add(i);
                }
                dist[i] = Math.min(dist[node]+1,dist[i]);
            }
        }

        System.out.println(Arrays.toString(dist));
    }

  //18. Word Ladder I
    String[] wordList = {"des","der","dfr","dgt","dfs"};
        String beginWord = "der";
        String endWord = "dfs";

        //Find the min transformations from beginword to end changing 1 letter at a time
        //Eg: hit -> hot -> lot -> log -> cog = 5 transformations

        Set<String> set = new HashSet<>();
        for(String i: wordList) set.add(i);

        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(beginWord,1));
        set.remove(beginWord);

        while(!q.isEmpty()) {
          Pair p = q.poll();
          String word = (String) p.a;
          if(word.equals(endWord)){
              System.out.println((int)p.b);
              break;
          }

          for(int i=0;i<word.length();i++) {
              for (char j = 'a'; j <= 'z'; j++) {
                  String newWord = word.substring(0,i) + j +word.substring(i+1);
                    if(set.contains(newWord)){
                        q.add(new Pair(newWord,(int)p.b+1));
                        set.remove(newWord);
                    }
              }
          }
        }

     //19. Djikstra Algorithm

       //a. Using PQ (can use Queue as well, same code, more TC)
        Pair[][] adj = {
                {new Pair(1,4),new Pair(2,4)},
                {new Pair(0,4),new Pair(2,2)},
                {new Pair(0,4),new Pair(1,2),new Pair(3,3),new Pair(4,1),new Pair(5,6)},
                {new Pair(2,3),new Pair(5,2)},
                {new Pair(2,1),new Pair(5,3)},
                {new Pair(2,6),new Pair(3,2),new Pair(4,3)},
        };

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->x.a-y.a);
        int[] dist = new int[adj.length];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[0] = 0; //source

        pq.add(new Pair(0,0)); //dist,node

        while (!pq.isEmpty()){
            Pair p = pq.poll();
            int node = p.b;
            int distance = p.a;

            for(Pair i:adj[node]){
                int weight = i.b;
                int adjNode = i.a;

                if(dist[adjNode]>weight+distance){
                    dist[adjNode] = weight+distance;
                    pq.add(new Pair(dist[adjNode],adjNode));
                }
            }
        }
        System.out.println(Arrays.toString(dist));

    // 18. Print shortest path (Hint: Keeping track of parent)
         Pair[][] adj = {
                {new Pair(1,4),new Pair(2,4)},
                {new Pair(0,4),new Pair(2,2)},
                {new Pair(0,4),new Pair(1,2),new Pair(3,3),new Pair(4,1),new Pair(5,6)},
                {new Pair(2,3),new Pair(5,2)},
                {new Pair(2,1),new Pair(5,3)},
                {new Pair(2,6),new Pair(3,2),new Pair(4,3)},
        };
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->x.a-y.a);
        int[] dist = new int[adj.length];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[0] = 0; //source

        int[] parent = new int[adj.length]; //keeps track of where we came from on that node

        for(int i=0;i< adj.length;i++) parent[i] = i;

        pq.add(new Pair(0,0)); //dist,node

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int node = p.b;
            int distance = p.a;

            for(Pair i:adj[node]){
                int adjNode = i.a;
                int weight = i.b;
                if(distance+weight<dist[adjNode]){
                    dist[adjNode] = weight+distance;
                    pq.add(new Pair(weight+distance,adjNode));
                    parent[adjNode] = node; //updating when we find shorter path to current node
                }

            }
        }

        int node = adj.length-1;
        while(parent[node]!=node){
            System.out.println(node);
            node = parent[node];
        }
        System.out.println(0); //src


//19. Shortest path in binary maze (Using djikstra algo)
   int[][] grid = {
                {1,1,1,1},
                {1,1,0,1},
                {1,1,1,1},
                {1,1,0,0},
                {1,0,0,0},
        };

        int[][] dist = new int[grid.length][grid[0].length];

        for(int[] i:dist) Arrays.fill(i,Integer.MAX_VALUE);

        Queue<Pair> q = new ArrayDeque<>();
        //Dont need a pq cause moving only takes 1 unit thereby queue stores in an increasing manner

        Pair src = new Pair(0,1);
        Pair dest = new Pair(2,2);

        q.add(new Pair(0,src.a,src.b)); //dist,r,c
        dist[src.a][src.b] = 0;

        while(!q.isEmpty()){
            Pair p = q.poll();
            int distance = p.a;
            int r = p.b;
            int c = p.c;

            if(r== dest.a && c== dest.b){
                System.out.println(distance);
                break;
            }

            int[] delRow = {-1,0,1,0};
            int[] delCol = {0,1,0,-1};

            for(int i=0;i<4;i++){
                int nrow = r + delRow[i];
                int ncol = c + delCol[i];

                if(nrow>=0 && nrow< grid.length && ncol>=0 && ncol< grid[0].length) {
                    if (grid[nrow][ncol] == 1) {
                        //can return the ans here also
                        if (dist[nrow][ncol] > distance + 1) {
                            dist[nrow][ncol] = distance + 1;
                            q.add(new Pair(distance + 1, nrow, ncol));
                        }
                    }
                }
            }

        }

  // 20. Path with minimum effort
   int[][] grid = {
                {1,2,2},
                {3,8,2},
                {5,3,5},

        };

        int[][] diff = new int[grid.length][grid[0].length];

        for(int[] i:diff) Arrays.fill(i,Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0,0,0)); //maxDifferenceInThatPath,r,c
        diff[0][0] = 0;

        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            int difference = p.a;
            int r = p.b;
            int c = p.c;

            if (r == 2 && c == 2) {
                System.out.println(difference);
                break;
            }

            int[] delRow = {-1, 0, 1, 0};
            int[] delCol = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nrow = r + delRow[i];
                int ncol = c + delCol[i];

                if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length) {
                    int newEffort = Math.max(Math.abs(grid[nrow][ncol]-grid[r][c]),difference);
                    if (diff[nrow][ncol] > newEffort) {
                            diff[nrow][ncol] = newEffort;
                            pq.add(new Pair(newEffort, nrow, ncol));
                        }

                }
            }
        }

  //21. Cheapest flights within k stops
         Pair[][] grid = {// dest,price
                {new Pair(1,5),new Pair(3,2)},
                {new Pair(2,5),new Pair(4,1)},
                {},
                {new Pair(1,2)},
                {new Pair(2,1)},
        };

        int src = 0;
        int dst = 2;
        int k = 2;

        Queue<Pair> q = new ArrayDeque<>();
        // no need of pq, as we are comparing stops, not distance and stops is increased by 1 during bfs,therefore is stored in inc manner

        q.add(new Pair(0,src,0)); //stops, src, distance

        int[] dist = new int[grid.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;

        while(!q.isEmpty()){
            Pair p = q.poll();
            int stops = p.a;
            int node = p.b;
            int distance = p.c;

            if(stops > k) continue;

            for(Pair i: grid[node]){
                int adjNode = i.a;
                int weight = i.b;

                if(dist[adjNode]>weight+distance && stops<=k){
                    dist[adjNode] = weight+distance;
                    q.add(new Pair(stops+1, adjNode, weight+distance));
                }

            }
        }

        System.out.println(dist[dst]);

     //21. Number of ways to arrive at dest with min time
      PriorityQueue<Pair> pq = new PriorityQueue<>();
            int[] dist = new int[grid.length];
            int[] ways = new int[grid.length]; //number of ways to reach that node in min dist

            Arrays.fill(dist,Integer.MAX_VALUE);

            dist[0] = 0;
            ways[0] = 1;

            pq.add(new Pair(0,0)); //dist, node

            while(!pq.isEmpty()){
                Pair p = pq.poll();

                int distance = p.a;
                int node = p.b;

                for(Pair i:grid[node]){
                    int adjNode = i.a;
                    int weight = i.b;

                    if(dist[adjNode]>weight+distance){
                        //arriving first time
                        dist[adjNode] = weight+distance;
                        pq.add(new Pair(weight+distance,adjNode));
                        ways[adjNode] = ways[node];
                    }else if(dist[adjNode]==weight+distance){
                        ways[adjNode] += ways[node];
                    }
                }
            }

  //22. Bellman Ford Algo (shortest dist with negative edges) (helps you to detect -ve cycles)
        (Only in directed graph)
     //Relaxing all the edges n-1 (0-based index) times sequentially

          Pair[] edges = {};

            int[] dist = new int[grid.length];
            Arrays.fill(dist,Integer.MAX_VALUE);
            dist[0] = 0;

            int V = 5; //vertices
            //edges given in question
            for(int i=0;i<V-1;i++){
                for(Pair p: edges){
                    //relaxation is just updating the distance
                    int u = p.a; //from
                    int v = p.b; //to
                    int wt = p.c; //wt

                    if(dist[u] != Integer.MAX_VALUE && dist[u]+wt<dist[v]){
                        dist[v] = dist[u] + wt;
                    }
                }
            }
            // after v-1 iterations, we get shortest distances

            //Nth relaxation to check neg cycle, if dist is reduced,means theres a -ve cycle

        for(Pair i:edges){
            int u = i.a; //from
            int v = i.b; //to
            int wt = i.c; //wt

            if(dist[u] != Integer.MAX_VALUE && dist[u]+wt<dist[v]){
                System.out.println(true);  //-ve cycle exists
                break;
            }
        }

   //23. Floyd Warshall Algo (multisource shortest path)
    (also helps to detect -ve cycle)
    //uses precomputed distances to get shortest dist (DP)
    //kind of a brute force

     int[][] matrix = {
                   {0,2,Integer.MAX_VALUE,Integer.MAX_VALUE},
                   {1,0,3,Integer.MAX_VALUE},
                   {Integer.MAX_VALUE,Integer.MAX_VALUE,0,Integer.MAX_VALUE},
                   {3,5,4,0},
           };
           // Integer.MAX_VALUE -> unreachable

        for(int via = 0;via< matrix.length;via++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][via]+matrix[via][j]);

                }
            }
        }

        //to detect -ve cycle
        for(int i=0;i< matrix.length;i++){
            if(matrix[i][i]<0) return true;
        }

 //24. Find the city with smallest number of neigbors at threshold dist

   //First, find smallest distances using Djikstra at all nodes or FLoyd Warshall

   //after getting dist matrix updated;
   int minCityCount = dist.length;
   int cityNum = -1;
   for(int i=0;i<dist.length;i++){
    int cnt = 0;
    for(int j=0;j<dist.length;j++){
        if(dist[i][j] <= threshold) cnt++;
      }
     if(cnt<=minCityCount){
            minCityCount = cnt;
            cityNum = i;
      }
    }

    return cityNum;


 //25. Prim's Algo to find MST
      PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] visited = new int[adj.length];

        pq.add(new Pair(0,0)); //wt,node (u can store parent as well if we want to get the edges as well
        int sum = 0;
        while(!pq.isEmpty()){
            Pair i = pq.poll();

            int node = i.a;
            int wtParent = i.b;

            if(visited[node]==1) continue;
            visited[node] = 1;
            sum+=wtParent;

            for(Pair j:adj[node]){
                int adjNode = j.a;
                int wt = i.b;

                if(visited[node]==0){
                    pq.add(new Pair(wt,adjNode));
                }

            }
        }

        System.out.println(sum);
 */

















    /*



        public void itr_inorderTraversal(Node root){
            if (root==null) return;
            Stack<Node> st = new Stack<>();
            st.push(root);

            while (!st.isEmpty()){
                Node top = st.peek();

                while(top.left!=null){
                    st.push(top.left);
                    top = st.peek();
                }

                while(!st.isEmpty() && st.peek().right==null){
                    System.out.print(st.pop().data+" ");
                }

                if(!st.isEmpty())
                    top = st.peek();

                if(top.right!=null) {
                    System.out.print(st.pop().data + " ");
                    st.push(top.right);
                }
            }

        }

    Work in progress

    public static void permutations(String str){
        ArrayList<String> combo = new ArrayList<>();
        Set<String> set = new HashSet<>();

        //for printing rotations of original str
        for(int i=0;i<str.length();i++){
            System.out.print(rotateString(str,i)+" ");
            set.add(rotateString(str,i));
        }

        //for getting swapped strs
        for(int i=0;i<str.length()-1;i++){
            for (int j=i+1;j<str.length();j++) {
                if (i == 0  && j==str.length()-1)
                    continue;

                    String swapped = swap(str, i, j);
                    combo.add(swapped);
                    set.add(swapped);

            }
        }

        //for printing rotations of swapped strs
        for(String i:combo){
            for(int j=0;j<str.length();j++){
                String rotated = rotateString(i,j);
                if(!set.contains(rotated))
                    System.out.print(rotated+" ");
            }
        }


    }*/