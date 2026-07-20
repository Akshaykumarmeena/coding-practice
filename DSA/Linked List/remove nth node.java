Remove Nth Node From End of List
  Given head of a linked list, remove the nth node from the END, return the head.
           

            Input: head = 1 → 2 → 3 → 4 → 5,  n = 2
            Output: 1 → 2 → 3 → 5          (removed 4 — the 2nd from the end)
            
            Input: head = 1,  n = 1
            Output: null                    (removed the only node)
            
            Input: head = 1 → 2,  n = 1
            Output: 1                       (removed 2)
            
          
/*
 * Problem: Remove Nth Node From End ⭐-worthy device
 * Pattern: Two pointers with gap + dummy node
 * Time: O(n) one pass, Space: O(1)
 * Key insight: gap of n between fast/slow → when fast.next==null, slow is BEFORE target
 * Key insight: dummy gives the head a predecessor → head-deletion isn't special
 * Key insight: return dummy.next (fresh read), NEVER head (stale photocopy)
 */


  static ListNode removeNode(ListNode head, int n){
		if(head==null) return null;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode i = dummy;
		ListNode j = head;
		int count = 0;
		while(count!=n -1) {
			j=j.next;
			count++;
		}
		while(j.next!=null) {
			i = i.next;
			j = j.next;
		}
		i.next = i.next.next;

	return dummy.next;
}

/*
static ListNode removeNode(ListNode head, int n){
		if(head==null) return null;
		if(head.next == null && n >= 1) return null;
		ListNode i = head;
		ListNode j = head;
		int count = 0;
		while(count!=n ) {
			j=j.next;
			count++;
		}
		while(j.next!=null) {
			i = i.next;
			j = j.next;
		}
		i.next = i.next.next;

	return head;
}
*/
