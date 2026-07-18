You're given an integer array nums and an integer target. You must assign either a + or a − sign to EVERY number in the array, then sum them all up. Return the number of different ways to assign signs so that the total equals target.
          
          
          Input: nums = [1,1,1,1,1], target = 3
          Output: 5
          Explanation: five sign-combinations reach 3, e.g. +1+1+1+1-1=3, +1+1+1-1+1=3, ...
          
          Input: nums = [1], target = 1
          Output: 1

  /*
 * Problem: Target Sum — DP #10, 0/1 knapsack COUNTING variant
 * REFRAME: +/- signs split nums into two piles P,N where P+N=S, P-N=target
 *   → P = (S+target)/2 — solve the algebra, turns into "how many subsets sum to P?"
 * Guards: (S+target) odd → 0 | P<0 → 0 | P>S → 0  (P is a subset-sum of non-negatives, bounded [0,S])
 * ways[s+x] += ways[s]  — COUNTING version of yesterday's possible[s+x] |= possible[s]
 * Base: ways[0] = 1 (one way to make sum 0 — pick nothing)
 * Reverse loop — same reuse-prevention as yesterday
 * Time O(n·P), Space O(P)
 */

static int targetSum(int[] nums, int target){
	int s = 0;
	for(int x: nums) s+=x;
	if((s+target)%2!=0) return 0;
	int p = (s+target)/2;
	if(p < 0 || p > s) return 0;
	int[] ways = new int[p+1];
	
	ways[0] = 1;
	for(int x: nums){
		for(int i = p; i>=0; i--){
			if(i+x <= p) ways[i+x] += ways[i];
		}	
	}
	return ways[p];
}
  
