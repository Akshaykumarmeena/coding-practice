Same setup as House Robber — nums[i] = cash in house i, adjacent houses trigger alarms, maximize loot — with ONE change: the houses are arranged in a CIRCLE. The first and last house are now neighbors of each other.
          
          Input: [2,3,2]
          Output: 3
          Explanation: can't rob house 0 AND house 2 (circle makes them adjacent) — rob house 1 alone
          
          Input: [1,2,3,1]
          Output: 4      (house 0 + house 2 — they're not adjacent even in the circle)
          
          Input: [1]
          Output: 1

  /*
 * Problem: House Robber II — DP #8, circular constraint
 * THE TRICK: circle's only crime = house 0 AND house n-1 together.
 *   Split: max( rob(0..n-2), rob(1..n-1) ) — each range is LINEAR, reuse HR-I verbatim.
 *   One circular problem = max of two straight-line problems.
 * Alternative (heavier): carry "took house 0?" flag → state doubles. Know it exists, don't build it.
 * Guards: n==1 explicitly (both ranges empty otherwise); size-1 range inside rob.
 * Range convention: j exclusive — pick once, keep everywhere.
 * Time O(n), Space O(1)
 */

  static int rob(int[] nums, int i, int j){   // j exclusive
    if(j - i == 1) return nums[i];
    int prev = nums[i];
    int curr = Math.max(nums[i], nums[i+1]);
    for(int p = i+2; p < j; p++){
        int next = Math.max(prev + nums[p], curr);
        prev = curr;
        curr = next;
    }
    return curr;
}
static int maxLoot(int[] nums){
	int n = nums.length;
	if(n==1) return nums[0];
	if(n==2) return Math.max(nums[0], nums[1]);
	return Math.max(rob(nums,0,n-1), rob(nums,1,n));
}
