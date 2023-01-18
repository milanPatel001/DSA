import java.util.*;

public class DisjointSet {
    int[] rank;
    int[] parent;

    public DisjointSet(int n){
        rank = new int[n];
        parent = new int[n];

        for(int i=0;i<=n;i++){
            rank[i] = 0;
            parent[i] = i;
        }
    }

    public int findUltParent(int node){
        if(node==parent[node]) return node;
        //path compression
        parent[node] = findUltParent(parent[node]);
        return parent[node];
    }

    public void unionByRank(int u, int v){
        //find ultParent of u and v
        int ultU = findUltParent(u);
        int ultV = findUltParent(v);

        if(ultU==ultV) return; //belonging to same component

        //attach smaller rank to larger rank
        if(rank[ultU] < rank[ultV]){
            parent[ultU] = ultV;
        }else if(rank[ultU] > rank[ultV]){
            parent[ultV] = ultU;
        }else{
            parent[ultV] = ultU;
            rank[ultU]++;
        }



    }
}
