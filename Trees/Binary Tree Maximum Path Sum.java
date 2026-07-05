Given a binary tree, find the maximum path sum. A path is any sequence of nodes where each pair of adjacent nodes has an edge connecting them. The path must contain at least one node and does not need to pass through the root. You cannot visit the same node twice.
        
  
  Example 1:
                1
               / \
              2   3
        
        Output: 6
        Explanation: 2 → 1 → 3
        Example 2:
               -3
               /
              2
        
        Output: 2
        Explanation: Taking just node 2 is better than including -3
        Example 3:
                -10
                /  \
               9   20
                   / \
                  15   7
        
        Output: 42
        Explanation: 15 → 20 → 7


  /*
 * Problem: Binary Tree Maximum Path Sum
 * Pattern: Tree DFS with global state tracking
 * Time: O(n), Space: O(h)
 * Key insight: each node can EITHER contribute to global max as an arch (left+node+right)
 * OR be returned upward as a single extending path (node + max(left,right)) — never both
 * Gotcha: drop negative contributions using Math.max(0, dfs(...))
 * Gotcha: use int[] array trick to mutate global max across recursive calls
 * Gotcha: initialize maxSum with Integer.MIN_VALUE, not 0 — handles all-negative trees
 */


static int dfs(TreeNode node, int[] maxSum){

if(node == null) return 0;

int left = Math.max(0, dfs(node.left, maxSum));
int right = Math.max(0, dfs(node.right, maxSum));

if( (node.val + left + right) > maxSum[0]) maxSum[0] = node.val+ left+ right;

return node.val + Math.max(left, right);

} 




static int maxPath(TreeNode root){

if(root == null) return 0;

int[] maxSum = {Integer.MIN_VALUE};

dfs(root, maxSum);

return maxSum[0];

}

  
