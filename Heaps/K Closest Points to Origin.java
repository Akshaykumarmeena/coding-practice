points[i] = [xᵢ, yᵢ], integer k → return the k points closest to origin (0,0). Distance = √(x²+y²). Any order.
        
        Input: points = [[1,3],[-2,2]], k = 1  →  [[-2,2]]     (dist²: 10 vs 8)
        Input: points = [[3,3],[5,-1],[-2,4]], k = 2  →  [[3,3],[-2,4]]


  /*
 * Problem: K Closest Points to Origin
 * Pattern: VIP room #3 — k SMALLEST via MAX-heap doorman (mirror of Kth Largest)
 * Time: O(n log k), Space: O(k)
 * Key insight: k smallest → room's WORST is its LARGEST → max-heap peek = eviction candidate
 * Key insight: compare x²+y² directly — sqrt preserves order, adds nothing but doubles
 * Comparator: (a,b) -> distSq(b) - distSq(a) for max-heap
 */


  static ArrayList<int[]> kClosestPoints(ArrayList<int[]> list, int k){
	int n = list.size();
	if(n<=1) return list;
	if( k > n ) return null;

	PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]) );
	for(int i=0; i<n;i++){
		if(heap.size() < k){
			heap.offer(list.get(i));
		}
		else{
			int[] tmp = heap.peek();
			int[] tmp1 = list.get(i);
			if( (tmp[0]*tmp[0] + tmp[1]*tmp[1]) > 	(tmp1[0]*tmp1[0] + tmp1[1]*tmp1[1]) ){
				heap.poll();
				heap.offer(tmp1);
			}
		}
	}

	ArrayList<int[]> result = new ArrayList<>();
	for(int i=0;i<k; i++){
		result.add(heap.poll());
	}
	
	return result;
}
