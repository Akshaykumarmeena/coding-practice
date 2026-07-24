Given a string s, find the length of the longest palindromic substring in s.

Input: s = "babad" → Output: "bab" (or "aba" — both length 3)
Input: s = "cbbd" → Output: "bb"

  /*
 * Problem: Longest Palindromic Substring — pattern: Expand Around Center
 * Time: O(n²), Space: O(1) (aside from small fixed-size pos arrays)
 * mock-tested: length-only version passed clean after 3-round redesign;
 *   full substring version: fail→pass (typos: substr→substring, coor→coord,
 *   PLUS a real aliasing bug — coord=pos1 copies reference not values,
 *   causing later loop iterations to silently corrupt an already-saved answer)
 * Key pattern reinforced: when saving "best so far" from a reused/mutable
 *   array, always copy values (new int[]{...} or Arrays.copyOf), never
 *   assign the reference directly — same lesson as early linked-list/graph work
 */



  import java.util.*;

public class Practice{

    static int expandFromCenter(String s, int left, int right, int[] pos){

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

            pos[0]=left+1;
            pos[1]=right-1;

            return count;
    }

   static String longestPalindrome(String s){

            int n = s.length();

            int maxLen = 0;

            int[] pos1 = {0,0};
            int[] pos2 = {0,0};
            int[] coord = {0,0};

            for(int i=0; i<n; i++){

                int a = expandFromCenter(s, i,i, pos1);
                int b = expandFromCenter(s,i,i+1, pos2);

                if(maxLen < Math.max(a,b)){
                    if(a > b){
                        coord = new int[]{pos1[0], pos1[1]};
                    } else coord = new int[]{pos2[0], pos2[1]};

                    maxLen = Math.max(a,b);
                }
                
            }

            return s.substring(coord[0], coord[1]+1);
   }

}
