Constraints:

2 <= nums.length <= 10^4
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i]. You must write an algorithm that runs in O(n) time, and you cannot use the division operator.

Input: nums = [1,2,3,4] → Output: [24,12,8,6]
Explanation: answer[0] = 2*3*4=24, answer[1] = 1*3*4=12, etc.

Input: nums = [-1,1,0,-3,3] → Output: [0,0,9,0,0]


/*
 * Problem: Product of Array Except Self
 * Pattern: Prefix/Suffix product arrays
 * Constraints: 2<=nums.length<=10^4, no division allowed, O(n) required
 * Time: O(n), Space: O(n) (O(1) extra if output array doesn't count,
 *   by folding prefix into the result array directly and suffix into a
 *   single running variable — a common follow-up optimization)
 * Key insight: answer[i] = (product of everything left of i) * (product
 *   of everything right of i) — two separate passes, no division needed
 * Why no division: breaks immediately with a single zero (divide by
 *   zero), and with TWO OR MORE zeros, division can't correctly
 *   reconstruct any position's answer at all
 */


static int[] product(int[] nums){
	int n = nums.length;
	int[] prefix = new int[n];
	int[] suffix = new int[n];
	int[] result = new int[n];
	
	prefix[0] = 1;
	for(int i=1; i<n; i++){
		prefix[i] = prefix[i-1]*nums[i-1]; 
	}

	suffix[n-1] = 1;
	for(int i = n-2; i >=0; i--){
		suffix[i] = suffix[i+1]*nums[i+1];
	}
	
	for(int i = 0; i<n;i++){
		result[i] = prefix[i] *suffix[i];
	}	
	
	return result;
}
