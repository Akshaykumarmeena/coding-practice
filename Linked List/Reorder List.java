Given the head of a singly linked list L₀ → L₁ → … → Lₙ₋₁ → Lₙ, reorder it in-place to:
L₀ → Lₙ → L₁ → Lₙ₋₁ → L₂ → Lₙ₋₂ → …
(first, last, second, second-to-last, third, ...)
          Input:  1 → 2 → 3 → 4
          Output: 1 → 4 → 2 → 3
          
          Input:  1 → 2 → 3 → 4 → 5
          Output: 1 → 5 → 2 → 4 → 3

  /*
 * Problem: Reorder List
 * Pattern: SYNTHESIS — slow/fast midpoint + in-place reverse + interleave weave
 * Time: O(n), Space: O(1)
 * Step 1: slow/fast finds middle; CUT (i.next = null) is mandatory — severs half1
 * Step 2: reverse second half in place (prev/curr/tmp, sacred order)
 * Step 3: symmetric interleave — save BOTH continuations, hook both, advance both
 * Gotcha: single-node guard returns head, not null
 * Gotcha: while(j!=null && j.next!=null) — && not ||, j first
 * Alt (mention-then-beat): ArrayList of nodes + two-index rewire — O(n) space
 */

  static ListNode reOrder(ListNode head){
		if(head == null || head.next == null) return head;
		// 1st part 
		ListNode i = head;
		ListNode j = head.next;
	
		while(j!=null && j.next!=null) {
			i=i.next;
			j=j.next.next;
		}	

		ListNode head2 = i.next;
		i.next = null;
		
		// now reversing the 2nd list
			ListNode prev = null;
			ListNode curr = head2;
			ListNode tmp;

			while(curr!=null){
				tmp = curr.next;
				curr.next = prev;
				prev = curr;
				curr = tmp;
			}
		head2 = prev;			
		
	// Now Merging the list

	i = head;
	j=head2;
	ListNode tmp1, tmp2;
	while(j != null){
   		 tmp1 = i.next;      // save half1's continuation
   		 tmp2 = j.next;      // save half2's continuation
  		 i.next = j;         // hook: 1 → 5
    		j.next = tmp1;      // hook: 5 → 2
   		i = tmp1;           // advance into half1
    		j = tmp2;           // advance into half2
	}

	return head;
}
