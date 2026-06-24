Given two strings s and t, return true if t is an anagram of s (uses exactly the same characters, same frequency, just rearranged), and false otherwise.
    
  Example:
          Input: s = "anagram", t = "nagaram"
          Output: true
          
          Input: s = "rat", t = "car"
          Output: false

/*
 * Problem: Valid Anagram
 * Pattern: Frequency array or HashMap
 * Time: O(n), Space: O(1) — character set is bounded
 * Key insight: increment for s, decrement for t, any non-zero at end = not anagram
 * Gotcha: String is immutable — toLowerCase() returns new String, must reassign
 * Gotcha: char[] cannot be used as HashMap key — use new String(charArray) instead
 * Alternative: sort both strings O(n log n) and compare with .equals()
 */


    
  // Method 1
  
  public boolean isAnagram(String s, String t){
    if(s.length() != t.length()) return false;

    char[] s1 = s.toCharArray();
    char[] t1 = t.toCharArray();

    Arrays.sort(s1);
    Arrays.sort(t1);

    for(int i=0; i<s1.length; i++){
        if(s1[i] != t1[i]) return false;
    }

    return true;
}





  
