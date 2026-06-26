Given the root of a binary tree, determine if it is a valid BST.
A valid BST:

Left subtree contains only nodes with values less than the node's value
Right subtree contains only nodes with values greater than the node's value
Both subtrees must also be valid BSTs

    Example:
    Valid:          Invalid:
        2               5
       / \             / \
      1   3           1   4
                         / \
                        3   6
    Output: true    Output: false
    (4's left child 3 is less than root 5 — violates BST property)


  /*
 * Problem: Validate Binary Search Tree
 * Pattern: Tree DFS with carried state (min/max bounds)
 * Time: O(n), Space: O(h)
 * Key insight: each node must satisfy (min, max) range, not just parent constraint
 * Going left: max becomes current val, min carried unchanged
 * Going right: min becomes current val, max carried unchanged
 * Gotcha: use Long not Integer — avoids edge cases when node val = Integer.MIN/MAX_VALUE
 * Gotcha: strict inequalities (<=, >=) — BST doesn't allow duplicate values
 * Gotcha: check current node against bounds, not children directly
 */


  static boolean isValid(TreeNode root, long min, long max){

if(root == null) return true;

if(root.val <= min || root.val >= max) return false;
return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
}

// to call the function

isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
