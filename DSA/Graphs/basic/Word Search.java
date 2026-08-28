Constraints:

1 <= board.length <= 6
1 <= board[i].length <= 6
1 <= word.length <= 15
board and word consist of only lowercase and uppercase English letters

Given an m x n grid of characters board and a string word, return true if word exists in the grid. The word must be constructed from letters of sequentially adjacent cells (horizontally or vertically neighboring), and the same cell may not be used more than once.

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false



/*
 * Problem: Word Search
 * Pattern: DFS + Backtracking (grid traversal with mark/unmark)
 * Constraints: 1<=board rows,cols<=6, 1<=word.length<=15
 * Time: O(m*n*4^L) worst case (L = word length, 4 directions per step),
 *   Space: O(L) recursion depth
 * Key insight: base case (k == word.length()) checked BEFORE the
 *   boundary/mismatch check — success doesn't need a valid i,j since
 *   there's no more character to match anyway
 * Key insight: mark cell as visited (overwrite with sentinel like '#')
 *   BEFORE recursing, restore original character AFTER — this is the
 *   backtracking "choose/explore/unchoose," letting OTHER paths reuse
 *   this cell once the current path abandons it
 * Key insight: dfs returns boolean directly and short-circuits via ||
 *   across all 4 directions — cleaner and safer than a side-effect flag,
 *   which risks a later failed branch overwriting an earlier success
 */
static boolean exist(char[][] board, String word){
    int m = board.length, n = board[0].length;
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(dfs(board, word, i, j, 0)) return true;
        }
    }
    return false;
}

static boolean dfs(char[][] board, String word, int i, int j, int k){
    if(k == word.length()) return true;
    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(k)) return false;
    
    char temp = board[i][j];
    board[i][j] = '#';
    
    boolean found = dfs(board, word, i+1, j, k+1) ||
                    dfs(board, word, i-1, j, k+1) ||
                    dfs(board, word, i, j+1, k+1) ||
                    dfs(board, word, i, j-1, k+1);
    
    board[i][j] = temp;
    
    return found;
}
