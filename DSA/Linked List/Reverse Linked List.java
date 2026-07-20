Given the head of a singly linked list, reverse it and return the new head.
        Input:  1 → 2 → 3 → 4 → 5 → null
        Output: 5 → 4 → 3 → 2 → 1 → null

  /*
 * Problem: Reverse Linked List
 * Pattern: Linked list — three-pointer walk (prev/curr/next)
 * Time: O(n), Space: O(1)
 * Key insight: SAVE curr.next before rewiring — it's your only bridge to the rest
 * Key insight: order is sacred: save → rewire → slide prev → slide curr
 * Key insight: prev ends on the new head; loop handles empty/single with no guards
 */


  static ListNode reverseList(ListNode head) {
              ListNode prev = null;
              ListNode curr = head;

              while(curr != null) {
                    ListNode next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
              }

              return prev;
}

