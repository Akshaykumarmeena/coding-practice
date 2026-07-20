Given a BST and two nodes p and q, find their lowest common ancestor (LCA). The LCA is the lowest node in the tree that has both p and q as descendants (a node can be a descendant of itself).
        

Example:
                6
               / \
              2   8
             / \ / \
            0  4 7  9
              / \
             3   5
        
        p = 2, q = 8
        Output: 6
        
        p = 2, q = 4
        Output: 2


/*
 * Problem: Lowest Common Ancestor of BST
 * Pattern: Tree DFS + BST property
 * Time: O(h), Space: O(h) recursive / O(1) iterative
 * Key insight: BST property tells you exactly which direction to go at each step
 * Both p,q < root → left. Both > root → right. Otherwise → root is LCA
 * Gotcha: root.val not root.value
 * Prefer iterative — O(1) space vs O(h) recursive call stack
 */



// Recursive — O(h) time, O(h) space
static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
    if(root == null) return null;
    if(root.val > p.val && root.val > q.val)
        return lowestCommonAncestor(root.left, p, q);
    if(root.val < p.val && root.val < q.val)
        return lowestCommonAncestor(root.right, p, q);
    return root;
}

// Iterative — O(h) time, O(1) space (preferred)
static TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q){
    while(root != null){
        if(root.val > p.val && root.val > q.val) root = root.left;
        else if(root.val < p.val && root.val < q.val) root = root.right;
        else return root;
    }
    return null;
}
