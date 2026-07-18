Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the input intervals.
              Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
              Output: [[1,6],[8,10],[15,18]]
              Explanation: [1,3] and [2,6] overlap (2 is inside 1-3) → merge into [1,6]
              
              Input: intervals = [[1,4],[4,5]]
              Output: [[1,5]]
              Explanation: touching counts as overlapping

  /*
 * Problem: Merge Intervals — pattern: Intervals (Sort + Greedy Sweep)
 * Step 1: sort by START — list.sort((a,b) -> a[0]-b[0])
 * Step 2: one linear pass, track maxEnd of CURRENT merged group (not just current interval's end!)
 * Overlap check: next.start <= maxEnd (touching counts — use <=, not <)
 * Gotcha: comparing against list.get(i)[1] instead of running maxEnd — loses info when
 *   a later interval is fully nested inside an earlier wider one (classic 3-interval trap)
 * Time O(n log n) — sort dominates. Space O(n) for result.
 */


  static List<int[]> mergeIntervals(List<int[]> list){
	int n = list.size();
	list.sort((a, b) -> a[0] - b[0]);
	int i=0;
	int p, q, maxEnd;
	List<int[]> result = new ArrayList<>();
	while(i<n){
		p = list.get(i)[0];
		maxEnd = list.get(i)[1];
		while((i+1) < n && list.get(i+1)[0] <= maxEnd){
			 i++;
			maxEnd = Math.max(maxEnd, list.get(i)[1]);	
		}
		i++;
		result.add(new int[]{p, maxEnd});
	}
	return result;
}
