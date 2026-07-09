The story: A game leaderboard shows the 3rd-highest score at all times. Players keep submitting new scores forever. Every time a score arrives, the board must instantly announce the current 3rd-highest.

Two moments in the machine's life:

Moment 1 — construction (happens once):
  javaKthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2});
The machine is born knowing: k=3, and four scores already exist. It sets up its internal state — your min-heap VIP room with 3 chairs — and processes those four initial scores into it. Room after setup: {4, 5, 8} (the 2 got rejected/evicted — weakest).

Moment 2 — add() (happens again and again, forever):
javakth.add(3);    // score 3 arrives → update room if needed → return whoever sits at peek()
kth.add(10);   // score 10 arrives → 10 > peek()=4 → evict 4, seat 10 → return new peek()=5


/*
 * Problem: Kth Largest Element in a Stream
 * Pattern: Min-heap of size K (the "VIP room" pattern)
 * Time: O(log k) per add, Space: O(k)
 * Key insight: K largest elements guarded by a MIN-heap — doorman only ever fights the weakest
 * Key insight: peek() of the size-K min-heap IS the Kth largest, always, for free
 * Three branches: size<k → offer | val>peek → poll+offer | else ignore
 * Equivalent: offer-then-trim (offer; if size>k poll) — handles not-full room automatically
 * Gotcha: FIELD DECLARATION ≠ CONSTRUCTION — new PriorityQueue<>() in constructor or NPE
 * Java: PriorityQueue = min-heap default; max: new PriorityQueue<>(Comparator.reverseOrder())
 */


class KthLargest {
    private PriorityQueue<Integer> heap;   // the room — survives across calls
    private int k;                          // the chair count — survives too

    public KthLargest(int k, int[] nums){
	this.heap = new PriorityQueue<>();
	this.k = k;
	for(int i=0;i<nums.length;i++){
		this.heap.offer(nums[i]);
	}
	for(int i=0;i<(nums.length-k);i++){
		this.heap.poll();
	}

    }

    public int add(int val){
	
	 if(this.k > heap.size()){
			this.heap.offer(val);
		}
	else if(val > this.heap.peek()){
			this.heap.poll();
			this.heap.offer(val);
		}
         return this.heap.peek();
    }
}
