Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence keeps the original order but may skip elements — [2,5,7] is a subsequence of [2,9,5,1,7].
            
            Input: nums = [10,9,2,5,3,7,101,18]
            Output: 4
            Explanation: [2,3,7,101] (or [2,5,7,101], [2,5,7,18]...) — length 4
            
            Input: nums = [0,1,0,3,2,3]
            Output: 4      ([0,1,2,3])
            
            Input: nums = [7,7,7,7]
            Output: 1      (strictly increasing — equal doesn't count)

  /*
 * Problem: Longest Increasing Subsequence — DP #5, FIRST SOLO DERIVATION
 * table[i] = length of LIS ENDING exactly at i; init all 1
 * table[i] = max over j<i where nums[j] < nums[i] of table[j]+1
 * ANSWER = max over whole table (not table[n-1]!)
 * Time O(n²), Space O(n) — mention O(n log n) tails+binary-search as follow-up
 */


  static int lis(int[] nums){
  	int n = nums.length;
  	int[] maxArray = new int[n];
  	Arrays.fill(maxArray, 1);
  	int output = 1;
  	for(int i=1; i<n; i++){
  		for(int j=0; j< i;j++){
  			if(nums[i] > nums[j]){
  				 if( maxArray[j] >= maxArray[i] )maxArray[i] = maxArray[j]+1;
  			}
  		}
  		if(maxArray[i] > output) output = maxArray[i];
  	}
  	return output;
}
