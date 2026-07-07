You are given the heads of two sorted linked lists list1 and list2. Merge them into one sorted list by splicing together the existing nodes (no creating new value nodes). Return the head of the merged list.
          Input:  list1 = 1 → 2 → 4,   list2 = 1 → 3 → 4
          Output: 1 → 1 → 2 → 3 → 4 → 4
          
          Input:  list1 = null,  list2 = null
          Output: null
          
          Input:  list1 = null,  list2 = 0
          Output: 0

  /*
 * Problem: Merge Two Sorted Lists
 * Pattern: Linked list — dummy node + tail weaving
 * Time: O(n+m), Space: O(1)
 * Key insight: DUMMY NODE kills the "empty result has no tail" special case —
 *   tail.next works uniformly from the first attach; answer = dummy.next
 * Key insight: splice existing nodes (tail.next = i), never copy values
 * Key insight: leftover attach is ONE rewire — remaining chain comes along, already sorted
 * Device to remember: dummy node → reusable in half of all list problems
 */

  static ListNode mergeList(ListNode list1, ListNode list2) {
    	if(list1 == null) return list2;
    	if(list2 == null) return list1;
    
    	ListNode i = list1;
    	ListNode j = list2;
    
    	ListNode dummy = new ListNode(-1);
    	ListNode tail = dummy;
    
    	while(i!=null && j!=null){
    		if(i.val <= j.val) {
    			tail.next = i;
    			i = i.next;
    		} else {
    			tail.next = j;
    			j = j.next;
    			}
    		tail = tail.next;
    	}
    
    	tail.next = (i!=null) ? i : j;
    	return dummy.next;
}
