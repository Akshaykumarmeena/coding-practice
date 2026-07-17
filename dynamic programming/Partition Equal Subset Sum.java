Given an integer array nums of positive integers, determine if it can be partitioned into two subsets such that the sum of elements in both subsets is equal.
        Input: nums = [1,5,11,5]
        Output: true
        Explanation: [1,5,5] and [11] — both sum to 11
        
        Input: nums = [1,2,3,5]
        Output: false
        Explanation: total sum = 11, odd — can't split into two equal halves

  /*
 * Problem: Partition Equal Subset Sum — DP #9, 0/1 knapsack family (first exposure)
 * REFRAME: two-equal-subsets ≡ "does some subset sum to total/2?" (subset-sum, not coin-change)
 * possible[s] = true if some subset sums to exactly s
 * For each number x: possible[s+x] |= possible[s]  — "add x to every already-reachable sum"
 * REVERSE loop (target→0) — each number used ONCE; forward loop would let x combine with itself
 * Base: possible[0] = true (empty subset, free). boolean[] default false = free sentinel.
 * Odd sum → immediately false (can't split evenly)
 * Time O(n·target), Space O(target)
 */

  static boolean isEqualSubset(int[] nums){
    	int sum = 0;
    	for(int x: nums){
    		sum+=x;
    	}
    
    	if(sum%2!=0) return false;
    	int target = sum/2;
    	
    	boolean[] possible = new boolean[target+1];
    	possible[0] = true;
    	for(int x: nums){
    		for(int s=target;s>=0; s--){
    			if(possible[s] && s+x<= target) possible[s+x] = true;
    		}
    		if(possible[target]) return true;
    	}
    	return false;
}

  
