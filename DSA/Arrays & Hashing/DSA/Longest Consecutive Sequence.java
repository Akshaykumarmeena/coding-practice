Constraints:

1 <= nums.length <= 6000
-10^9 <= nums[i] <= 10^9

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence. You must write an algorithm that runs in O(n) time.

Input: nums = [100,4,200,1,3,2] → Output: 4
Explanation: the longest consecutive sequence is [1,2,3,4], length 4

Input: nums = [0,3,7,2,5,8,4,6,0,1] → Output: 9

/*
 * Problem: Longest Consecutive Sequence
 * Pattern: HashSet, O(n) via smart start-detection
 * Time: O(n), Space: O(n)
 * Key insight: only count forward from a number if x-1 is NOT in the set
 *   (i.e., x is a true sequence start) — this ensures each number is only
 *   ever counted once across the whole algorithm, keeping it O(n) overall
 *   despite the nested-looking structure
 * Gotcha: inside the counting loop, must advance x itself (x=x+1), not
 *   just increment the counter — forgetting this causes an infinite loop
 * Gotcha: the running max must be declared ONCE outside the outer loop,
 *   not reset inside it every iteration
 */

  static int count(int x, HashSet<Integer> set){
    int t = 1;
    while(set.contains(x+1)){
        x = x+1;   // advance x, or your loop never progresses
        t++;
    }
    return t;
}

static int maxSeq(int[] nums){
    HashSet<Integer> set = new HashSet<>();
    for(int x : nums) set.add(x);
    
    int max = 1;  // declared ONCE, outside the loop
    for(int x : set){  // iterate over the SET, not the original array (avoids redundant work on duplicates)
        if(!set.contains(x-1)){
            max = Math.max(max, count(x, set));
        }
    }
    return max;
}
