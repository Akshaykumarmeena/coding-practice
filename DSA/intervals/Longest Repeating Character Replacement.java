You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character, at most k times. Return the length of the longest substring containing the same letter you can get after performing the above operations.

  Constraints:
  
  1 <= s.length <= 10^5
  s consists of only uppercase English letters
  0 <= k <= s.length
  
  Input: s = "ABAB", k = 2 → Output: 4
  Explanation: replace the two 'A's with 'B's (or vice versa) → "BBBB" or "AAAA"
  
  Input: s = "AABABBA", k = 1 → Output: 4
  Explanation: replace one 'A' at position 3 with 'B' → subsequence "BABB"
  (positions 1 to 4) or "ABBB" — becomes all same character with 1 change


/*
 * Problem: Longest Repeating Character Replacement
 * Pattern: Sliding Window with frequency tracking
 * Time: O(n), Space: O(1) (fixed 26-size array)
 * Constraints: 1 <= s.length <= 10^5, s consists of uppercase English
 *   letters only, 0 <= k <= s.length
 * mock-tested: multiple rounds, rebuilt piece-by-piece after initial
 *   confusion — final version correct and traced against provided example
 * Key insight: within any window, minimum changes needed = windowSize -
 *   maxFreq (change everyone who isn't the majority character to match it)
 * Key insight: maxFreq is allowed to go "stale" (not decremented when
 *   shrinking) — this never causes a wrong (too-large) answer, because a
 *   stale (too-high) maxFreq only makes the validity check STRICTER,
 *   causing at most one extra unnecessary shrink, never an incorrect
 *   acceptance of an invalid window
 * Shrink rule: when invalid, shrink by exactly ONE position (not a while
 *   loop) — since window grows by at most 1 net per outer step, one shrink
 *   is always sufficient to restabilize
 */

```java
static int maxLen(String s, int k){

        int[] freq = new int[26];

        int left = 0, right = 0, maxFreq = 0, best = 0, count, windowSize;

        while(right < s.length()){
            char c = s.charAt(right);
            freq[c - 'A']++;

            count = freq[c - 'A'];

            maxFreq = Math.max(maxFreq, count);

            windowSize = right - left + 1;

            if(windowSize - maxFreq <= k){
                best = Math.max(windowSize, best);
            }
            else{
                freq[s.charAt(left) - 'A']--;
                left++;
            } 

            right++;

        }

        return best;

    }
```
