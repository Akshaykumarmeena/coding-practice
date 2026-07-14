You're climbing a staircase with n steps. Each move, you climb 1 or 2 steps. How many distinct ways to reach the top?
        n = 2 → 2      (1+1, or 2)
        n = 3 → 3      (1+1+1, 1+2, 2+1)
        n = 4 → 5
        n = 5 → 8

  /*
 * Problem: Climbing Stairs — DP problem #1
 * Pattern: 1D DP, Fibonacci-shaped
 * Time: O(n), Space: O(n) array → O(1) rolling variables
 * THE DP SENTENCE: ways(n) = ways(n-1) + ways(n-2), because the last move
 *   came from exactly one of two places — big answer built from smaller answers
 * Base: ways(0)=1 (one way: do nothing), ways(1)=1
 * Gotcha: arr size n+1 → last index n — return arr[n]
 */

  static int fib(int n){
	if(n <= 1) return 1;
	int[] arr = new int[n+1];
	arr[0]=1;
	arr[1]=1;
	for(int i=2;i<n+1;i++){
		arr[i] = arr[i-1]+arr[i-2];
	}
	return arr[n];
}


// To make it O(1) space

int prev = 1, curr = 1;
for(int i = 2; i <= n; i++){
    int next = prev + curr;
    prev = curr;
    curr = next;
}
return curr;
