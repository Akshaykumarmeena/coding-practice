There are numCourses courses labeled 0 to numCourses-1. You're given an array prerequisites where prerequisites[i] = [a, b] means: to take course a, you must first take course b.
Return true if you can finish all courses, false otherwise.
          Input: numCourses = 2, prerequisites = [[1,0]]
          Output: true
          Explanation: take 0 first, then 1. Fine.
          
          Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
          Output: false
          Explanation: 1 needs 0 first, but 0 needs 1 first. Deadlock — impossible.
          
          Input: numCourses = 5, prerequisites = [[1,0],[2,1],[3,2],[4,2]]
          Output: true
          Explanation: order 0→1→2→3→4 works (3 and 4 in either order after 2)


/*
 * Problem: Course Schedule ⭐
 * Pattern: Directed graph cycle detection — 3-state DFS
 * Time: O(V+E), Space: O(V+E)
 * Key insight: visited alone insufficient — must distinguish "on MY current path" (1) 
 *   from "fully explored earlier" (2). Meeting 1 = cycle. Meeting 2 = harmless merge.
 * Key insight: mark 1 on ENTRY, 2 on EXIT (after neighbor loop) — Clone Graph's 
 *   put-before-recurse, third appearance
 * Gotcha: loop ALL courses in main (disconnected graph) — Islands' scanner
 * Gotcha: getOrDefault for courses with no outgoing edges — bare get returns null
 */


static boolean canFinish(int numCourses, int[][] prerequisites){
    // 1. Build adjacency list: b → list of courses that depend on b
    HashMap<Integer, List<Integer>> adj = new HashMap<>();
    for(int[] pair : prerequisites){
        int a = pair[0], b = pair[1];
        adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }
    
    // 2. State array: 0 = unvisited, 1 = in progress (red), 2 = done (green)
    int[] state = new int[numCourses];   // all 0 by default — you know why
    
    // 3. Try DFS from every course (graph may be disconnected)
    for(int c = 0; c < numCourses; c++){
        if(state[c] == 0){
            if(!dfs(c, adj, state)) return false;   // cycle found anywhere → impossible
        }
    }
    return true;   // no DFS found a cycle
}

static boolean dfs(int course, HashMap<Integer, List<Integer>> adj, int[] state){
    if(state[course] == 1) return false;   // RED breadcrumb — my own path — CYCLE
    if(state[course] == 2) return true;    // GREEN — already proven safe — skip
    
    state[course] = 1;                     // ENTRY: drop red breadcrumb
    
    for(int next : adj.getOrDefault(course, new ArrayList<>())){
        if(!dfs(next, adj, state)) return false;   // cycle somewhere below → bubble it up
    }
    
    state[course] = 2;                     // EXIT: everything below is clean → green
    return true;
}
