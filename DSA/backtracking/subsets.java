Given an array of unique integers nums, return ALL possible subsets (the power set). No duplicate subsets. Any order.
      Input: nums = [1,2,3]
      Output: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
      
      Input: nums = [0]
      Output: [[], [0]]

  static void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result){
    result.add(new ArrayList<>(path));        // record current subset (see THE TRAP)
    
    for(int i = start; i < nums.length; i++){
        path.add(nums[i]);                     // CHOOSE
        backtrack(nums, i + 1, path, result);  // EXPLORE (deeper, with choice made)
        path.remove(path.size() - 1);          // UNCHOOSE ← the backtrack itself
    }
}
static List<List<Integer>> subset(int[] nums){
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		backtrack(nums, 0, path, result);
		return result;
}

/*


Level 0 shuru: backtrack(start=0), path=[]

📸 photo of [] → result = [[]]
loop shuru, i=0: path mein 1 daala → path=[1]
recursive call... Level 0 yahan PAUSE ho gaya. Bookmark: "i=0 par hun, call ke baad remove karna hai."

Level 1 shuru: backtrack(start=1), path=[1]

📸 photo of [1] → result = [[], [1]]
loop shuru, i=1: path mein 2 daala → path=[1,2]
recursive call... Level 1 PAUSE. Bookmark: "i=1, remove pending."

Level 2 shuru: backtrack(start=2), path=[1,2]

📸 photo of [1,2] → result = [[], [1], [1,2]]
loop: i=2, 2 < 2 false → loop chala hi nahi
function khatam → return


Level 2 khatam hua → Level 1 apne bookmark se JAAG gaya. Woh i=1 par tha, recursive call wali line par. Uske baad ki line kya hai? path.remove — sirf EK baar:

path.remove → path=[1]
ab Level 1 ka loop aage badhta hai: i++ → i=2 → 2 < 2 false → Level 1 ka loop khatam → return

Level 1 khatam → Level 0 apne bookmark se jaaga. Woh i=0 par tha:

path.remove → path=[]
AB DHYAN SE — Level 0 ka loop abhi zinda hai! i++ → i=1 → 1 < 2 TRUE → loop ka AGLA round chalega!
i=1: path mein nums[1]=2 daala → path=[2]
recursive call → Level 1' shuru: backtrack(start=2), path=[2]

📸 photo of [2] → result = [[], [1], [1,2], [2]]
loop nahi chala → return


Level 0 jaaga: path.remove → path=[]
i++ → i=2 → loop khatam → return

Final: [[], [1], [1,2], [2]] — chaaron subsets ✓

*/
