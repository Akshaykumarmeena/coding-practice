A robot sits at the top-left corner of an m × n grid. It can only move right or down, one cell at a time. It wants to reach the bottom-right corner. How many distinct paths exist?
          Input: m = 3, n = 7
          Output: 28
          
          Input: m = 3, n = 2
          Output: 3
          Explanation: right→down→down, down→down→right, down→right→down
          
          Input: m = 1, n = 1
          Output: 1

  

  /*
 * Problem: Unique Paths — DP #6, first 2D table, SECOND SOLO DERIVATION
 * mat[i][j] = mat[i-1][j] + mat[i][j-1]  (only two ways to arrive)
 * Base: first row & column = 1 (single straight path along edges)
 * Time O(m·n), Space O(m·n) — reducible to O(n) rolling row (mention it)
 * Derivation method that worked: draw small tables → observe → generalize. Keep it.
 */


  static int paths(int m, int n){
	if(m==1 || n==1) return 1;
	int[][] mat = new int[m][n];
	for(int i=0; i<m; i++) mat[i][0] = 1;
	for(int j=0; j<n; j++) mat[0][j] = 1;

	for(int i=1; i< m; i++){
		for(int j=1; j<n; j++) {
			mat[i][j] = mat[i-1][j] + mat[i][j-1];
		}
	 }
	return mat[m-1][n-1];
}

// TO make it in O(n) space

static int paths(int m, int n){
	if(m==1 || n==1) return 1;
	int[] mat = new int[n];
	for(int j=0; j<n; j++) mat[j]= 1;

	for(int i=1; i< m; i++){
		for(int j=1; j<n; j++) {
			mat[j] = mat[j] + mat[j-1];
		}
	 }
	return mat[n-1];
}
