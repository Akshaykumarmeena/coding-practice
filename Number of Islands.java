Given an m x n 2D grid of '1's (land) and '0's (water), return the number of islands. An island is formed by connecting adjacent land cells horizontally or vertically (not diagonally).
          Input:
          grid = [
            ['1','1','0','0','0'],
            ['1','1','0','0','0'],
            ['0','0','1','0','0'],
            ['0','0','0','1','1']
          ]
          
          Output: 3


  /*
 * Problem: Number of Islands
 * Pattern: Graph DFS on grid (flood fill)
 * Time: O(m*n), Space: O(m*n) worst-case recursion depth
 * Key insight: TWO separate jobs — scanner (nested loops, counts islands) + sinker (DFS, marks visited)
 * Key insight: sink visited land by setting to 0 — grid itself acts as visited set
 * Gotcha: boundary check is >= m, not > m — indices run 0 to m-1
 * Gotcha: validate at function entry, don't pre-check before each recursive call
 * Gotcha: count lives in scanner, NOT in dfs — increments per island, not per cell
 */
  
  
  static void dfs(int[][] grid, int i, int j, int m, int n){
    if(i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == 0) return;
    
// sink current cell
grid[i][j]=0;
dfs(grid, i+1, j, m, n);
dfs(grid, i-1, j, m, n);
dfs(grid, i, j+1, m, n);
dfs(grid, i, j-1, m, n);
}

static int islands(int[][] grid){
    int m = grid.length;
    if(m == 0) return 0;
    int n = grid[0].length;
    
    int count = 0;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 1){
                count++;           // found a new island
                dfs(grid, i, j, m, n);  // sink all of it
            }
        }
    }
    return count;
}
