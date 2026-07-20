Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words. The same word may be reused any number of times.
        
        Input: s = "leetcode", wordDict = ["leet","code"]
        Output: true
        Explanation: "leetcode" = "leet" + "code"
        
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: "apple" + "pen" + "apple" — reuse allowed
        
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false
        Explanation: every segmentation attempt strands some letters —
          "cats"+"and" leaves "og"; "cat"+"sand" leaves "og"; nothing covers it

  /*
 * Problem: Word Break — DP #4, boolean table + cut-point
 * can[i] = ∃ j<i : can[j] && dict.contains(s.substring(j,i))
 * Base: can[0]=true (empty prefix = done). boolean[] default false = free sentinel.
 * Time O(n²·substring), Space O(n)
 */


  static boolean wordBreak(String s, String[] wordDict){
	HashSet<String> dict = new HashSet<>();
	for(String c: wordDict){
		dict.add(c);
	}
	int n = s.length();
	boolean[] can = new boolean[n+1];
	can[0] = true;
	for(int i=1;i<n+1;i++){
		for(int j = 0; j< i; j++){
			if(dict.contains(s.substring(j, i)) && can[j]){
				can[i] = true; 
				break;
			}
		}
	}

	return can[n];
}
