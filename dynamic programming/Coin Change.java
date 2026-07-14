You are given an integer array coins representing coin denominations, and an integer amount. Return the fewest number of coins needed to make up exactly that amount. If the amount cannot be made with any combination of the coins, return -1. You have infinite supply of each coin.
          Input: coins = [1,2,5], amount = 11
          Output: 3
          Explanation: 11 = 5 + 5 + 1 (three coins — no way to do it in two)
          
          Input: coins = [2], amount = 3
          Output: -1
          Explanation: 2, 2+2=4 — 3 is unreachable with only 2s
          
          Input: coins = [1], amount = 0
          Output: 0
          Explanation: zero coins make zero amount

  /*
 * Problem: Coin Change — DP problem #3, first "min over choices" recurrence
 * Pattern: 1D DP, unbounded choices
 * THE RECURRENCE: best(a) = 1 + min over coins c≤a of best(a−c)
 *   — "the last coin was one of these; try all, trust none" (greedy FAILS: [1,3,4],6 → DP:2, greedy:3)
 * Base: best[0] = 0 — THE SEED; forget it and the whole table stays impossible
 * Sentinel: impossible = value guaranteed bigger than any real answer (amount+1, or
 *   MAX_VALUE + overflow guard). -1 only at the door, NEVER inside the table.
 * Time O(amount·coins), Space O(amount)
 */

static int coinChange(int[] nums, int amount){
	if(amount == 0) return 0;
	int[] best = new int[amount+1];
	Arrays.fill(best, Integer.MAX_VALUE);
	best[0] = 0;
	for(int i=1; i <= amount; i++) {
		for(int c: nums){
			if(c <= i && best[i-c]!= Integer.MAX_VALUE){
				best[i] = Math.min(best[i], 1+best[i-c]);
			}
		}
	}
	return best[amount] == Integer.MAX_VALUE ? -1: best[amount];
}
