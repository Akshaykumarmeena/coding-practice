Given head of a linked list, return true if the list contains a cycle — i.e., some node's next points back to an earlier node, so walking the list never reaches null — else false.
            Input: 3 → 2 → 0 → -4
                        ↑________|      (-4's next points back to node 2)
            Output: true
            
            Input: 1 → 2 → null
            Output: false


  /*
 * Problem: Linked List Cycle (Floyd's tortoise & hare)
 * Pattern: Slow/fast pointers
 * Time: O(n), Space: O(1)
 * Key insight: fast gains +1 per step on slow inside a cycle → must collide; no cycle → fast hits null
 * Key insight: guard BOTH j and j.next in the while — j first (short-circuit); odd lists crash otherwise
 * Key insight: slow-null check unnecessary — fast always dies first
 * Start j = head.next to avoid the trivial i==j at step zero (or compare after moving)
 * Interview: mention HashSet brute force (O(n) space) THEN present this
 */


  static boolean isCycle(ListNode head){
    	if(head == null) return false;
    	ListNode i = head;
    	ListNode j = head.next;
    
    	while(j!=null && j.next!=null){
    		if(i==j) return true;
    		i=i.next;
    		j=j.next.next;
    	}
	return false;
}
