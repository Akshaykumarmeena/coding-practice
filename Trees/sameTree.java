Given the roots of two binary trees p and q, return true if they are the same tree (same structure AND same values), false otherwise.
        
  Example:
        Input:
        Tree 1:     Tree 2:
            1           1
           / \         / \
          2   3       2   3
        
        Output: true
        
        Input:
        Tree 1:     Tree 2:
            1           1
           / \         / \
          2              2
        
        Output: false


  /*
 * Problem: Same Tree
 * Pattern: Tree DFS (Recursion)
 * Time: O(n), Space: O(h)
 * Key insight: three base cases — both null (true), one null (false), both not null (recurse)
 * Gotcha: don't ignore return values of recursive calls — use && to combine results
 * Gotcha: check current node values BEFORE recursing into children
 * Cleaner null check: if(p==null || q==null) return false — after both-null case handled
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
