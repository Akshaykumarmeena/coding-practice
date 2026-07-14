You're robbing houses along a street. nums[i] = cash in house i. Adjacent houses have linked alarms — rob two neighbors and police come. Maximize the total loot.
          Input: [1,2,3,1]     → 4      (rob house 0 and house 2: 1+3)
          Input: [2,7,9,3,1]   → 12     (rob 0, 2, 4: 2+9+1)
          Input: [2,1,1,2]     → 4      (rob 0 and 3 — skipping TWO in a row is allowed!)


  /*
 * Problem: House Robber — DP problem #2, first real DECISION
 * Pattern: 1D DP, choice recurrence, O(1) rolling space
 * THE RECURRENCE: rob(i) = max(nums[i] + rob(i-2),  rob(i-1))
 *                            └─ rob it: nearest legal partner   └─ skip: inherit
 * Base: rob(0)=nums[0], rob(1)=max(nums[0],nums[1])
 * Magic sentence #2: the subproblem answer already CONTAINS every earlier
 *   decision — including multi-skips ([2,1,1,2]→4). Formula ≠ "alternate houses".
 * Time O(n), Space O(1)
 */

  static int maxLoot(int[] nums){

	int n = nums.length;
	if(n==1) return nums[0];
	if(n==2) return Math.max(nums[0], nums[1]);
	int prev = nums[0];
	int curr = Math.max(nums[1], prev);
	int next;
	
	for(int i=2;i<n;i++){
		next = Math.max((nums[i]+prev), curr);
		prev = curr;
		curr = next;
	}
	return curr;
}
