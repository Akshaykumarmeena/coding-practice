Given the root of a binary tree, return the length of the diameter — the longest path between any two nodes in the tree, measured by the number of edges on that path. The path may or may not pass through the root.
         
  Example 1:
                  1
                 / \
                2   3
               / \
              4   5
          
          Output: 3
          Explanation: longest path is 4 → 2 → 1 → 3 (or 5 → 2 → 1 → 3), which has 3 edges
          Example 2:
              1
             /
            2


  static int dfs(TreeNode node, int[] maxDiameter){
    if(node == null) return 0;  // height of null is 0
    
    int leftHeight = dfs(node.left, maxDiameter);
    int rightHeight = dfs(node.right, maxDiameter);
    
    // arch — update global max, NOT returned
    maxDiameter[0] = Math.max(maxDiameter[0], leftHeight + rightHeight);
    
    // returned to parent — height of THIS subtree, not the diameter
    return 1 + Math.max(leftHeight, rightHeight);
}

static int diameterOfBinaryTree(TreeNode root){
    int[] maxDiameter = {0};
    dfs(root, maxDiameter);
    return maxDiameter[0];
}
