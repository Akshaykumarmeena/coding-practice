Given two strings s and p, return an array of all the start indices of p's anagrams in s.

    Input: s = "cbaebabacd", p = "abc" → Output: [0, 6]
    Explanation: substring starting at 0 ("cba") and at 6 ("bac") are anagrams of "abc"
    
    Input: s = "abab", p = "ab" → Output: [0, 1, 2]

/*
 * Problem: Find All Anagrams in a String
 * Pattern: Fixed-size sliding window with incremental frequency matching
 * Time: O(m) — one pass, O(1) work per slide via addChar/removeChar
 * Space: O(distinct chars in p)
 * mock-tested: fail (many rounds) → clean rebuild provided
 * Key insight: track `matches` = count of characters whose window-frequency
 *   currently EQUALS p's required frequency. Window is a full anagram when
 *   matches == pCount.size() (all distinct chars in p are satisfied)
 * Key insight: addChar/removeChar use BEFORE/AFTER count comparison —
 *   check if the old count WAS matching (now broken) vs new count NOW
 *   matches (newly fixed) — never re-scan the whole map on each slide
 * Gotcha: two DIFFERENT characters slide each step — outgoing (leaving
 *   window) and incoming (entering window) — using the same variable for
 *   both is a common copy-paste bug that silently breaks the window update
 */


import java.util.*;

public class Practice{

    static int addChar(char c, HashMap<Character,Integer> windowCount, HashMap<Character,Integer> pCount, int matches){
        int oldCount = windowCount.getOrDefault(c, 0);
        int newCount = oldCount + 1;
        windowCount.put(c, newCount);

        int needed = pCount.getOrDefault(c, 0);

        if(newCount == needed) matches++;
        else if(oldCount == needed) matches--;

        return matches;
    }

    static int removeChar(char c, HashMap<Character,Integer> windowCount, HashMap<Character,Integer> pCount, int matches){
        int oldCount = windowCount.get(c);
        int newCount = oldCount - 1;
        if(newCount == 0) windowCount.remove(c);
        else windowCount.put(c, newCount);

        int needed = pCount.getOrDefault(c, 0);

        if(newCount == needed) matches++;
        else if(oldCount == needed) matches--;

        return matches;
    }

    static List<Integer> anagramCount(String s, String p){
        int m = s.length();
        int n = p.length();

        List<Integer> result = new ArrayList<>();
        if(n > m) return result;

        HashMap<Character, Integer> pCount = new HashMap<>();
        for(char c : p.toCharArray()){
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> windowCount = new HashMap<>();
        int matches = 0;

        // build initial window using addChar for consistency
        for(int i = 0; i < n; i++){
            matches = addChar(s.charAt(i), windowCount, pCount, matches);
        }

        if(matches == pCount.size()) result.add(0);

        for(int j = n; j < m; j++){
            char outgoing = s.charAt(j - n);
            char incoming = s.charAt(j);

            matches = removeChar(outgoing, windowCount, pCount, matches);
            matches = addChar(incoming, windowCount, pCount, matches);

            if(matches == pCount.size()) result.add(j - n + 1);
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println(anagramCount("cbaebabacd", "abc")); // [0, 6]
        System.out.println(anagramCount("abab", "ab"));         // [0, 1, 2]
    }

}
