Given two strings text1 and text2, return the length of their longest common subsequence — the longest sequence of characters appearing in BOTH strings in the same relative order (not necessarily contiguous). If none, return 0.
          Input: text1 = "abcde", text2 = "ace"
          Output: 3
          Explanation: "ace" appears in both (a_c_e within abcde)
          
          Input: text1 = "abc", text2 = "abc"
          Output: 3
          
          Input: text1 = "abc", text2 = "def"
          Output: 0

  /*
 * Problem: Longest Common Subsequence — DP #7, two-sequence 2D table
 * dp[i][j] = LCS of first i chars of s1 vs first j chars of s2
 * (m+1)×(n+1), row0/col0 = 0 (empty prefix) — FREE from Java defaults, no fill loops
 * match     → 1 + dp[i-1][j-1]   (DIAGONAL — clean base, current chars excluded)
 * no match  → max(dp[i-1][j], dp[i][j-1])   (inherit best — this IS the inheritance)
 * NEVER max-then-add: above/left may already contain the current char → double count
 *   (killer example: "bbb" vs "b" → 3 instead of 1)
 * Strings read at charAt(i-1) — table is shifted by one
 * Time O(m·n), Space O(m·n) → O(n) rolling with one saved diagonal variable (footnote)
 */


  static int lcs(String s1, String s2){
  	int m = s1.length();
  	int n = s2.length();
  
  	if(m==0 || n==0) return 0;
  	int[][] mat = new int[m+1][n+1];
  	for(int i=0;i<m+1;i++)  mat[i][0]=0;
  	for(int j=0;j<n+1; j++) mat[0][j]=0;
  	
  	for(int i=1;i<=m;i++){
  		for(int j=1; j<=n; j++){
  			if(s1.charAt(i-1) == s2.charAt(j-1)) mat[i][j] =1 + mat[i-1][j-1]; 
  			else mat[i][j] = Math.max(mat[i-1][j], mat[i][j-1]);
  		}
  	}
  	return mat[m][n];
}
