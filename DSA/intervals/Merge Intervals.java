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



####################################### Method 2 #########################################################################################################################



/*
 * Problem: Merge Intervals
 * Pattern: Sort + Greedy Sweep
 * Constraints: 1<=intervals.length<=10^4, 0<=start<=end<=10^4
 * Time: O(n log n) — sort dominates, Space: O(n) for result
 * Key insight: sort by start first. Track a RUNNING merged interval
 *   (tmp), comparing the NEXT interval's start against tmp's CURRENT
 *   extent (tmp[1]) — not against any single interval's own raw end.
 *   This distinction matters because tmp[1] may already be extended
 *   beyond any individual interval's end due to earlier merges (e.g. a
 *   large interval fully containing several smaller ones).
 * Gotcha: use next[0] <= tmp[1] (or tmp[1] < nextStart for the non-
 *   overlap branch) — touching intervals (end == next start) should
 *   still merge, per the problem's rule
 * Gotcha: when saving a completed group to result, use new int[]{...}
 *   (a snapshot), never add a reused/mutable array reference directly —
 *   same aliasing trap as Backtracking's path.add() vs new ArrayList<>(path)
 */



static List<int[]> finalInterval(List<int[]> list){
    List<int[]> result = new ArrayList<>();
    list.sort((a, b) -> Integer.compare(a[0], b[0]));
    int n = list.size();
    int i = 0;
    int[] tmp = new int[2];
    tmp[0] = list.get(0)[0];
    tmp[1] = list.get(0)[1];  // ALSO initialize tmp[1] up front, not just tmp[0]
    
    while(i < n-1){
        int nextStart = list.get(i+1)[0];
        int nextEnd = list.get(i+1)[1];
        
        if(tmp[1] < nextStart) {  // compare against the RUNNING merged end, not the raw current interval's end
            result.add(new int[]{tmp[0], tmp[1]});
            tmp[0] = nextStart;
            tmp[1] = nextEnd;
        } else {
            tmp[1] = Math.max(tmp[1], nextEnd);  // extend the merge if overlapping
        }
        i++;
    }
    result.add(new int[]{tmp[0], tmp[1]});  // also fix the final add to use the snapshot syntax
    return result;
}
