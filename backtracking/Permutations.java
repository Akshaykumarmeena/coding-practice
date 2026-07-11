Given an array of distinct integers nums, return all possible orderings (permutations).
          Input: [1,2,3]
          Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]     (3! = 6)
          
          Input: [0,1]  →  [[0,1],[1,0]]


/*
 * Problem: Permutations ⭐ (needs ownership trace — assembled today, not derived)
 * Pattern: Backtracking — full-depth photo + used-register
 * Time: O(n·n!), Space: O(n)
 * Three diffs from Subsets:
 *   ① photo ONLY at path.size()==nums.length, then return
 *   ② no start — loop from 0 every level (order matters now)
 *   ③ used-set guard — "attendance register" of the bag
 * IRON RULE: choose = add to BOTH (path+used); unchoose = remove from BOTH — symmetry todi toh sab toota
 * Alt implementations (revision week): boolean[] used by index; swap-based
 */




static void backtracking(int[] nums, List<Integer> path, HashSet<Integer> used, List<List<Integer>> result){

    if(path.size() == nums.length){          // ① photo SIRF full depth par
        result.add(new ArrayList<>(path));
        return;                               // aage kuch nahi bacha — wapas
    }

    for(int i = 0; i < nums.length; i++){     // ② start NAHI — har level par sab options
        if(used.contains(nums[i])) continue;  // ③ guard: yeh already path mein hai? skip

        path.add(nums[i]);                    // CHOOSE — dono registers mein entry:
        used.add(nums[i]);                    //   bag mein bhi, hisaab-kitaab mein bhi

        backtracking(nums, path, used, result);

        path.remove(path.size() - 1);         // UNCHOOSE — dono se wapas:
        used.remove(nums[i]);                 //   symmetry TODI toh sab toota
    }
}

static List<List<Integer>> permutations(int[] nums){
	int n = nums.length;
	
	List<List<Integer>> result = new ArrayList<>();
	List<Integer> path = new ArrayList<>();
	HashSet<Integer> used = new HashSet<>();

	backtracking(nums, path, used, result);

	return result;
}
