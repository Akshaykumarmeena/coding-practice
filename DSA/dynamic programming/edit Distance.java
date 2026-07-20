Given two strings word1 and word2, return the minimum number of operations required to convert word1 into word2.
Allowed operations, each counts as one step:

Insert a character
Delete a character
Replace a character

    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: horse → rorse (replace 'h' with 'r')
                 rorse → rose (delete 'r')
                 rose → ros (delete 'e')
    
    Input: word1 = "intention", word2 = "execution"
    Output: 5

  /*
 * Problem: Edit Distance — DP #11, LCS's sibling (2D string table)
 * dp[i][j] = min ops to convert word1[0..i) into word2[0..j)
 * Match: dp[i][j] = dp[i-1][j-1] (no cost, inherit diagonal)
 * No match: 1 + min(delete=dp[i-1][j], insert=dp[i][j-1], replace=dp[i-1][j-1])
 * Base: dp[i][0]=i (delete all), dp[0][j]=j (insert all)
 * WHY NOT GREEDY: killer example "ab"→"ba" — greedy commits early, misses that
 *   the right choice depends on what's further down both strings. DP tries all,
 *   keeps the cheapest, because smaller subproblems are already solved first.
 * Time O(m·n), Space O(m·n) → reducible to O(n) rolling row (footnote)
 */


  static int editDistance(String s1, String s2){
	int m = s1.length();
	int n = s2.length();
	if(m == 0 ) return n;
	if(n==0) return m;

	int[][] dp = new int[m+1][n+1];
	for(int i=0; i<=m; i++) dp[i][0] = i;
	for(int j=0; j<=n; j++) dp[0][j] = j;
	for(int i=1;i<=m;i++){
		for(int j=1; j<=n; j++){
			if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
			else { dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min (dp[i][j-1], dp[i-1][j-1])); }
		}
	}
	return dp[m][n];

}
