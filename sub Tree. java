Given the roots of two binary trees root and subRoot, return true if there is a subtree of root that has the same structure and values as subRoot, false otherwise.
         
          
          Example:
          root:           subRoot:
              3               4
             / \             / \
            4   5           1   2
           / \
          1   2
          
          Output: true
          (the left subtree of root matches subRoot exactly)

/*
 * Problem: Subtree of Another Tree
 * Pattern: Tree DFS (Recursion) + reusing isSameTree
 * Time: O(m*n), Space: O(h)
 * Key insight: for each node in root, check if isSameTree — else recurse left and right
 * Gotcha: use isSubtree (not isSameTree) for left/right recursive calls
 * Gotcha: subRoot==null → true (empty tree is always a subtree)
 * Base cases: subRoot null → true, root null → false
 */


 static boolean isEqual(TreeNode p, TreeNode q){
        if(p==null && q==null) return true;
        if(p == null || q == null) return false;
        if(p!=null && q!=null){
            if(p.val!=q.val) return false;
            else return isEqual(p.left, q.left) && isEqual(p.right, q.right);
        }
        
        return true;
    }
    
    static boolean isSubTree(TreeNode root, TreeNode subRoot){
        if(subRoot == null) return true;
        if(root == null) return false;
        
        return isEqual(root, subRoot) || isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
    }
