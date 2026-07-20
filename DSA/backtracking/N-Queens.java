Place n queens on an n×n chessboard so that no two queens attack each other. A queen attacks everything in her row, column, and both diagonals. Return ALL distinct solutions, each as a list of strings.
              
              Input: n = 4
              Output: [
                [".Q..",     [  "..Q.",
                 "...Q",        "Q...",
                 "Q...",        "...Q",
                 "..Q."],       ".Q.."]
              ]
              (2 solutions for n=4; n=1 → 1 solution; n=2,3 → 0 solutions)


  static void backtrack(int n, List<Integer> path, List<List<String>> result,
                      HashSet<Integer> cols, HashSet<Integer> sumDiag, HashSet<Integer> diffDiag){
    int row = path.size();                    // depth IS the row — no row loop
    if(row == n){
        result.add(buildBoard(path, n));      // photo = expand columns to strings
        return;
    }
    for(int col = 0; col < n; col++){         // ONE loop: choose this row's column
        if(cols.contains(col) || sumDiag.contains(row+col) || diffDiag.contains(row-col))
            continue;

        path.add(col);                         // sign all FOUR records
        cols.add(col);
        sumDiag.add(row+col);
        diffDiag.add(row-col);

        backtrack(n, path, result, cols, sumDiag, diffDiag);

        path.remove(path.size()-1);            // unchoose: List by index...
        cols.remove(col);                      // ...Sets by VALUE
        sumDiag.remove(row+col);
        diffDiag.remove(row-col);
    }
}

static List<String> buildBoard(List<Integer> path, int n){
    List<String> board = new ArrayList<>();
    for(int r = 0; r < n; r++){
        char[] rowChars = new char[n];
        Arrays.fill(rowChars, '.');
        rowChars[path.get(r)] = 'Q';
        board.add(new String(rowChars));
    }
    return board;
}

static List<List<String>> nQueens(int n){
    List<List<String>> result = new ArrayList<>();
    backtrack(n, new ArrayList<>(), result, new HashSet<>(), new HashSet<>(), new HashSet<>());
    return result;
}
