You have an m x n grid of heights. The Pacific ocean touches the grid's top and left edges; the Atlantic ocean touches the bottom and right edges.
Rain water on any cell can flow to a neighboring cell (up/down/left/right) only if the neighbor's height is less than or equal to the current cell's height — water flows downhill or level, never uphill.
Return a list of all cells [i, j] from which water can flow to BOTH oceans.
                
                  Input:
                  heights = [
                    [1,2,2,3,5],
                    [3,2,3,4,4],
                    [2,4,5,3,1],
                    [6,7,1,4,5],
                    [5,1,1,2,4]
                  ]
                  
                  Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
                  
                  E.g. cell [2,2] (height 5): water can flow 5→3→1 rightward/down to Atlantic,
                  and 5→4→2 leftward/up to Pacific. Reaches both → included.


  /*
 * Problem: Pacific Atlantic Water Flow
 * Pattern: Multi-source grid DFS — REVERSED direction
 * Time: O(m*n), Space: O(m*n)
 * Key insight: don't ask "can each cell reach the ocean" (O((mn)²)) —
 *   flip it: flood UPHILL from each ocean's edges, mark reachable cells
 * Key insight: two boolean grids, answer = cells true in BOTH
 * Key insight: dfs marks SELF, carries prevHeight param, recurses unconditionally —
 *   base case rejects out-of-bounds / visited / downhill
 * Gotcha: HashSet<int[]> can NEVER work — arrays don't override equals/hashCode (3rd time!)
 * Gotcha: pass Integer.MIN_VALUE from seeds so edge cells always accept themselves
 */


  static void dfs(int i, int j, boolean[][] ocean, int[][] grid, int prevHeight){
    if(i >= grid.length || i < 0 || j >= grid[0].length || j < 0) return;
    if(ocean[i][j]) return;                    // visited
    if(grid[i][j] < prevHeight) return;        // can't climb uphill INTO a lower cell
    
    ocean[i][j] = true;                        // mark SELF
    
    dfs(i+1, j, ocean, grid, grid[i][j]);
    dfs(i-1, j, ocean, grid, grid[i][j]);
    dfs(i, j+1, ocean, grid, grid[i][j]);
    dfs(i, j-1, ocean, grid, grid[i][j]);
}


static ArrayList<int[]> waterFlow(int[][] grid){
	int m = grid.length;
	int n = grid[0].length;
	boolean[][] pacific = new boolean[m][n];
	boolean[][] atlantic = new boolean[m][n];

	for(int i=0; i< m; i++) {
		dfs(i, 0, pacific, grid , Integer.MIN_VALUE);
		dfs(i, n-1, atlantic, grid,  Integer.MIN_VALUE);
	}

	for(int j=0; j<n; j++){
		dfs(0, j, pacific, grid,  Integer.MIN_VALUE);
		dfs(m-1, j, atlantic, grid,  Integer.MIN_VALUE);
	}

	// now taking the common out
	ArrayList<int[]> output = new ArrayList<>();

	for(int i=0; i<m; i++) {
		for(int j=0; j<n; j++) {
		if(atlantic[i][j] && pacific[i][j]) output.add(new int[]{i, j});
			}
		}
		return output;
}
