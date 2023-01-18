import java.util.*;

public class Graph {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.insert(1,2);
        g.insert(2,4);
        g.insert(1,3);
        g.insert(3,4);

        g.topologicalSort();
    }

    public static class Node {
            int data;

            public Node(int data) {
                this.data = data;
            }
    }

    Map<Integer, List<Integer>> map = new HashMap<>();

          public boolean isCyclicBFSUndirected(){
                     Map<Integer, Boolean> visited = new HashMap<>();
                       for(int i:map.keySet()){
                           boolean ans = isCyclicBFSUndirected(i,visited);
                           if(ans) return true;
                       }
                       return false;
                  }

          private boolean isCyclicBFSUndirected(int src, Map<Integer,Boolean> visited){
            Map<Integer, Integer> parent = new HashMap<>();
            visited.put(src,true);
            parent.put(src,-1);

            Queue<Integer> q = new ArrayDeque<>();
            q.add(src);

            while(!q.isEmpty()){
                int front = q.poll();

                for(int i:map.get(front)){
                    if(visited.getOrDefault(i,false) && parent.getOrDefault(front,-1) == i){
                        return true;
                    }
                    else if(!visited.getOrDefault(i, false)){
                        visited.put(i,true);
                        q.add(i);
                        parent.put(i,front);
                    }
                }
            }
            return false;
          }

    public boolean isCyclicDFSUndirected(){
        Map<Integer, Boolean> visited = new HashMap<>();
        for(int i:map.keySet()){
            boolean ans = isCyclicDFSUndirected(i,visited,-1);
            if(ans) return true;
        }
        return false;
    }

    private boolean isCyclicDFSUndirected(int src, Map<Integer,Boolean> visited,int parent){
        visited.put(src,true);

        for(int i:map.get(src)){
            if(!visited.getOrDefault(i,false)) {
                boolean ans = isCyclicDFSUndirected(i, visited, src);
                if (ans) return true;
            }else if(i!=parent) return true;
        }
        return false;

    }

    public void topologicalSort(){
        Map<Integer, Boolean> visited = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for (int i:map.keySet()){
            if(!visited.getOrDefault(i,false))
                topologicalSort(i,visited,st);
        }

        while(!st.isEmpty()) System.out.println(st.pop());
    }

    private void topologicalSort(int src,Map<Integer,Boolean> visited,Stack<Integer> st){
        visited.put(src,true);

        if(map.get(src)!=null) {
            for (int i : map.get(src)) {
                if (!visited.getOrDefault(i, false)) {
                    topologicalSort(i, visited, st);
                }
            }
        }
        st.push(src);
    }


    public void insert(int from, int to) {

            List<Integer> list = map.getOrDefault(from, new ArrayList<Integer>());
            list.add(to);
            map.put(from, list);


        }

        public void dfs(int start) {
            Map<Integer, Boolean> visited = new HashMap<>();
            for (var i : map.keySet())
                dfs(i, visited);
        }

        private void dfs(int start, Map<Integer, Boolean> visited) {
            if (map.get(start) == null) {
                if (!visited.getOrDefault(start, false)) {
                    System.out.println(start);
                    visited.put(start, true);
                }
                return;
            }

            for (int i = 0; i < map.get(start).size(); i++) {
                if (visited.getOrDefault(start, false)) {
                    return;
                } else {
                    System.out.println(start);
                    visited.put(start, true);
                    dfs(map.get(start).get(i), visited);
                }
            }

        }

        public void bfs(int start) {
            Map<Integer, Boolean> visited = new HashMap<>();
            for (var i : map.keySet())
                if(!visited.getOrDefault(i,false))
                    bfs(i, visited);
        }

        private void bfs(int start, Map<Integer, Boolean> visited) {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            visited.put(start, true);

            while (!q.isEmpty()) {
                int top = q.poll();

                System.out.println(top);

                if(map.get(top)!=null) {
                    for (var i : map.get(top)) {
                        if (!visited.getOrDefault(i, false)) {
                            q.add(i);
                            visited.put(i, true);
                        }
                    }
                }
            }
        }
}
