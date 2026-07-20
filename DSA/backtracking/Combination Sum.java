Given an array of distinct integers candidates and a target, return all unique combinations where the chosen numbers sum to target. The same number may be chosen unlimited times. Any order.
              Input: candidates = [2,3,6,7], target = 7
              Output: [[2,2,3], [7]]
              
              Input: candidates = [2,3,5], target = 8
              Output: [[2,2,2,2], [2,3,3], [3,5]]
              
              Input: candidates = [2], target = 1
              Output: []

  /*
 * Problem: Combination Sum
 * Pattern: Backtracking — remaining-target + reuse via i
 * Time: O(exponential, bounded by target/min), Space: O(target/min) depth
 * Base cases: target==0 → photo+return | target<0 → return
 * THE TAXONOMY (flashcard this):
 *   pass i+1 → each element once, no look-back        (Subsets)
 *   pass i   → reuse me, no look-back → no dup orders (Combination Sum)
 *   pass 0 + used-register → all orderings             (Permutations)
 * Pruning: if(target < nums[i]) continue — optional but saves calls
 */

  
static void backtracking(int start, int[] nums, int target, List<Integer> path, List<List<Integer>> result){

    if(target < 0) return;
    if(target == 0){         
        result.add(new ArrayList<>(path));
        return;                              
    }


    for(int i = start; i < nums.length; i++){     
        if(target < nums[i] ) continue;  

        path.add(nums[i]);                    

        backtracking(i, nums, target-nums[i], path, result);

        path.remove(path.size() - 1);     
    }
}

static List<List<Integer>> combinationSum(int[] nums, int target){
	int n = nums.length;
	
	List<List<Integer>> result = new ArrayList<>();
	List<Integer> path = new ArrayList<>();
	backtracking(0, nums, target, path, result);

	return result;
}
