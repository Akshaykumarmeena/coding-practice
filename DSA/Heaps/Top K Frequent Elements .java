Given an integer array nums and integer k, return the k most frequent elements. Any order.

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1, 2]        (1 appears 3×, 2 appears 2×, 3 appears 1× → top two: 1 and 2)
    
    Input: nums = [1], k = 1
    Output: [1]


  /*
 * Problem: Top K Frequent Elements
 * Pattern: HashMap frequency count + heap selection
 * V1 (mine): max-heap of ALL entries, poll k → O(n log n), O(n) space
 * V2 (follow-up): min-heap ROOM of size k by frequency → O(n log k), O(k) space
 * V3 (optimal, revision): bucket sort by frequency → O(n)
 * Comparator law: (a,b) -> a[1]-b[1] = min/ascending; b[1]-a[1] = max/descending
 * Pair trick: int[]{num, count} — no custom class needed
 * Gotcha: entry.getValue() — parens, it's a method
 */

  static int[] topFrequent(int[] nums, int k){
    	int n = nums.length;
    	if(n==1) return new int[]{nums[0]};
    	HashMap<Integer, Integer> map = new HashMap<>();
    	for(int i=0;i<n;i++){
    		map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    	}
    	
    	PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
    		heap.offer(new int[]{entry.getKey(), entry.getValue()});
    	}
    	int[] output = new int[k];
    	int[] tmp;
    	for(int i=0;i<k;i++){
    		tmp = heap.poll();
    		output[i]=tmp[0];
    	}
    	return output;
}

// the above one was O(nlogn) to make it )(nlogk), we need to add

PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
for(Map.Entry<Integer, Integer> entry : map.entrySet()){
    heap.offer(new int[]{entry.getKey(), entry.getValue()});
    if(heap.size() > k) heap.poll();
}
