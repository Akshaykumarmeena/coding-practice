Given the root of a binary tree, invert it (mirror it left to right) and return the root.
        
Example:
        Input:          Output:
                4               4
               / \             / \
              2   7           7   2
             / \ / \         / \ / \
            1  3 6  9       9  6 3  1

/*
 * Problem: Invert Binary Tree
 * Pattern: Tree DFS (Recursion)
 * Time: O(n), Space: O(h)
 * Key insight: swap left and right children at each node, recurse on both
 * Gotcha: Java is pass-by-value — swapping references in a separate function won't work
 * Gotcha: inline swap directly on node.left and node.right, or pass parent node to swap function
 * Base case: node == null, not node.left == null && node.right == null
 */


static TreeNode inverse(TreeNode node){
    if(node==null) return null;
    inverse(node.left);
    inverse(node.right);
    
    TreeNode temp = node.left;
    node.left = node.right;
    node.right = temp;
    
    return node;
}


// Method 2

static TreeNode inverse(TreeNode node){
    if(node==null) return null;
    
    TreeNode temp = node.left;
    node.left = inverse(node.right);
    node.right = inverse(temp);
    
    return node;
}

// To do it via swap function
// You're passing node (the parent) — so inside swap, parent.left and parent.right are mutations on the actual object, not reassignments of copies

static void swap(TreeNode parent){
    TreeNode temp = parent.left;
    parent.left = parent.right;
    parent.right = temp;
}

static void inverse(TreeNode node){
    if(node==null) return;
    inverse(node.left);
    inverse(node.right);
    swap(node);  // pass the parent, not the children
}
