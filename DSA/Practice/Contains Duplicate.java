Given an integer array nums, return true if any value appears at least twice in the array, and false if every element is distinct.

Input: nums = [1,2,3,1] → true
Input: nums = [1,2,3,4] → false


  /*
 * Problem: Contains Duplicate
 * Pattern: HashSet
 * Time: O(n), Space: O(n)
 * mock-tested: fail→pass (1 round — return values inverted: found-duplicate
 *   should return true, completed-clean should return false)
 * Key insight: check-before-add — if value already in set, duplicate found;
 *   otherwise add and continue
 *
 * ALTERNATE (space-optimized): sort nums first, then linear scan checking
 * nums[i] == nums[i-1] (duplicates become adjacent after sorting).
 * Time: O(n log n) [sort dominates], Space: O(1) extra.
 * Tradeoff: better space, worse time than the HashSet version — mention
 * this verbally if asked "can you reduce space?" rather than assuming
 * it's a strict improvement.
 */


import java.util.*;

public class Practice{

    static boolean isDuplicate(int[] nums){

        HashSet<Integer> set = new HashSet<>();

        for(int x: nums){
            if(set.contains(x)) return true;
            set.add(x);
        }

        return false;

    }

}
