Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai, bi <= n
ai != bi
No repeated edges

You have a graph of n nodes labeled 1 to n, forming a tree with exactly one extra edge added (creating exactly one cycle). Return the redundant edge — the one that, if removed, restores a valid tree. If multiple answers exist, return the one occurring last in the input.

Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]



/*
 * Problem: Redundant Connection
 * Pattern: Union-Find (Disjoint Set Union)
 * Constraints: 3<=n<=1000, nodes labeled 1 to n, edges[i].length==2
 * Time: O(n * α(n)) ≈ O(n) effectively, Space: O(n)
 * Key insight: process edges ONE AT A TIME in input order — for each,
 *   check if the two nodes ALREADY share the same root (already connected
 *   through some other path). If so, THIS edge is redundant — return it
 *   immediately (order matters: must process in input order to get the
 *   correct "last redundant edge" per problem's tie-breaking rule)
 * Key insight: find() walks the parent-chain up to the ultimate root —
 *   NOT just checking the immediate parent — so two nodes can become
 *   "the same root" indirectly, through a chain built by earlier unions
 * Gotcha: parent[] MUST be explicitly initialized (parent[i]=i for every
 *   node) — Java's default 0s make every node think node 0 is the leader,
 *   breaking find() completely
 * Gotcha: union() must merge at the ROOT level (parent[rootA]=rootB), never
 *   directly connect the input nodes a,b themselves — bypassing find()
 *   corrupts the whole chain-following mechanism
 */


static int[] edgeExist(List<int[]> edges, int n){
    int[] parent = new int[n+1];  // nodes are 1 to n, so size n+1 to allow index n
    for(int i = 1; i <= n; i++){
        parent[i] = i;
    }
    
    for(int[] pair : edges){
        int a = pair[0];
        int b = pair[1];
        
        if(!union(parent, a, b)){
            return pair;  // this is the redundant edge — return immediately
        }
    }
    
    return new int[]{-1, -1};  // shouldn't happen given the problem's guarantees
}

static int find(int[] parent, int x){
    while(parent[x] != x) x = parent[x];
    return x;
}

static boolean union(int[] parent, int a, int b){
    int rootA = find(parent, a);
    int rootB = find(parent, b);
    if(rootA == rootB) return false;
    parent[rootA] = rootB;
    return true;
}
