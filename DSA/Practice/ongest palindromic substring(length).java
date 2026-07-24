Given a string s, find the length of the longest palindromic substring in s.

Input: s = "babad" → Output: "bab" (or "aba" — both length 3)
Input: s = "cbbd" → Output: "bb"

  /*
 * Problem: Longest Palindromic Substring (length-only version)
 * Pattern: Expand Around Center
 * Time: O(n²) — n centers, O(n) expansion each. Space: O(1)
 * mock-tested: fail→fail→pass (3 rounds: declaration-order bug, return-type 
 *   mismatch + confused center-indexing, then clean rebuild)
 * Key insight: every palindrome has a center — 2n-1 possible centers total
 *   (n single-char odd centers, n-1 between-char even centers)
 * left==right seeds odd case (count=1, pre-expand once); left!=right (adjacent)
 *   handles even case (count=0) — same loop body then handles both uniformly
 * TODO: extend to return actual substring — track (start,end) of best match
 *   instead of just maxLen, then s.substring(start, end+1) at the end
 */

  import java.util.*;

public class Practice{

    static int expandFromCenter(String s, int left, int right){

            int n = s.length();


            int count;
            if(left==right) {
                count = 1;
                left--;
                right++;
            }
            else count = 0;

            while(left >=0 && right < n){
                if(s.charAt(left) == s.charAt(right)){
                    count+=2;
                    left--;
                    right++;
                } 
                else break;
            }

            return count;
    }

   static int longestPalindrome(String s){

            int n = s.length();

            int maxLen = 0;

            for(int i=0; i<n; i++){
                
                maxLen = Math.max(maxLen, Math.max(expandFromCenter(s, i,i), expandFromCenter(s,i,i+1)));
            }

            return maxLen;
   }

}
