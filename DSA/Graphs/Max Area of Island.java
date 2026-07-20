Max Area of Island
Given an m x n grid of 0s (water) and 1s (land), return the area of the largest island — the number of cells in the biggest connected group of 1s (connected horizontally or vertically). If there is no island, return 0.
        Input:
        grid = [
          [0,0,1,0,0],
          [0,1,1,0,0],
          [0,1,0,0,1],
          [0,0,0,1,1]
        ]
        
        Output: 4
        Explanation: the island containing (0,2),(1,1),(1,2),(2,1) has 4 cells — the largest


  /*
 * Problem: Max Area of Island
 * Pattern: Grid DFS returning accumulated value
 * Time: O(m*n), Space: O(m*n) worst-case recursion
 * Key insight: dfs returns area sunk = 1 + sum of 4 directional calls
 * Gotcha: prefer return-value accumulation over int[] trick when results flow upward
 */

  /////////   Method 1

  static int dfs(int[][] grid, int i, int j, int m, int n){
    if(i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == 0) return 0;
    
    grid[i][j] = 0;
    
    return 1 + dfs(grid, i+1, j, m, n)
             + dfs(grid, i-1, j, m, n)
             + dfs(grid, i, j+1, m, n)
             + dfs(grid, i, j-1, m, n);
}

static int maxArea(int[][] grid){
    int m = grid.length;
    if(m == 0) return 0;
    int n = grid[0].length;
    
    int maxArea = 0;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1){
                maxArea = Math.max(maxArea, dfs(grid, i, j, m, n));
            }
        }
    }
    return maxArea;
}





//////// Method 2


static void dfs(int[][] grid, int i, int j, int m, int n, int[] count){
    if(i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == 0) return;
    	
	// sink current cell
	grid[i][j]=0;
	count[0]++;
	dfs(grid, i+1, j, m, n, count);
	dfs(grid, i-1, j, m, n, count);
	dfs(grid, i, j+1, m, n, count);
	dfs(grid, i, j-1, m, n, count);

	
}

static int islands(int[][] grid){
    int m = grid.length;
    if(m == 0) return 0;
    int n = grid[0].length;
    
    int[] count = {0};

   int maxCount = 0;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1){
               // count[0]++;           // found a new island
                dfs(grid, i, j, m, n, count);  // sink all of it
		
	       maxCount = Math.max(count[0], maxCount);
	       count[0]=0;
            }
        }
    }
    return maxCount;
}
