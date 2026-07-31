1 <= n <= 10^4

Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two if there exists an integer x such that n == 2^x.

Input: n = 1 → Output: true (2^0 = 1)
Input: n = 16 → Output: true (2^4 = 16)
Input: n = 3 → Output: false


  /*
 * Problem: Power of Two
 * Pattern: Math / bit manipulation
 * Constraints: 1 <= n <= 10^4 (also valid conceptually for larger n, e.g.
 *   full int range, though this constraint set caps it at 10^4)
 * Time: O(log n) via repeated halving, Space: O(1)
 * mock-tested: pass, first attempt, clean
 * Key insight: repeatedly halve; if at any point (before reaching 1) the
 *   number is odd, it can't be a power of 2
 * Follow-up O(1) trick: n > 0 && (n & (n-1)) == 0 — a power of two has
 *   exactly one bit set in binary; n-1 flips all bits below that bit,
 *   so ANDing gives 0 only for powers of two
 */

  ```java
import java.util.*;

public class Practice{

    static boolean isPowerOf2(int n){

        while(true){
            if(n%2!=0 && n!=1) return false;
            if(n==1) return true;
            n/=2;
        }

    }




    public static void main(String[] args){

       System.out.println(isPowerOf2(1));
       System.out.println(isPowerOf2(16));
       System.out.println(isPowerOf2(3));
    }

}

```
