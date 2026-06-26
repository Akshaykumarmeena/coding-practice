Given a binary tree, a node is good if in the path from root to that node, there are no nodes with a value greater than the node's value.
            
  
  Example:
                    3
                   / \
                  1   4
                 /   / \
                3   1   5
            
            Output: 4
            Good nodes: 3(root), 3(left->left), 4(right), 5(right->right)


/*
 * Problem: Count Good Nodes in Binary Tree
 * Pattern: Tree DFS (Recursion) with carried state
 * Time: O(n), Space: O(h)
 * Key insight: carry maxSoFar down — node is good if val >= maxSoFar
 * Key insight: combine left+right counts with addition, not separate returns
 * Gotcha: can't have two return statements — add results together instead
 * Start with Integer.MIN_VALUE so root always counts as good
 */


  static int total(TreeNode root, int maxSoFar){
	
	if(root == null) return 0;

	if(root.val >= maxSoFar) {
		maxSoFar = root.val;
		return 1+total(root.left, maxSoFar) + total(root.right, maxSoFar);
	} else return total(root.left, maxSoFar) + total(root.right, maxSoFar);    
}

// call it like

return total(root, Integer.MIN_VALUE);
