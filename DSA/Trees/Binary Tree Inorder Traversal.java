Given the root of a binary tree, return the inorder traversal of its values as a list (left → root → right).
          
  Example:
                  4
                 / \
                2   6
               / \ / \
              1  3 5  7
          
          Output: [1, 2, 3, 4, 5, 6, 7]

/*
 * Problem: Binary Tree Inorder Traversal
 * Pattern: Tree DFS (Recursion)
 * Time: O(n), Space: O(n)
 * Key insight: recursion naturally mirrors tree structure — base case is null node
 * Preorder: add → left → right
 * Inorder: left → add → right (gives sorted order for BST)
 * Postorder: left → right → add
 * Gotcha: always check node == null first before accessing node.val or node.left/right
 */

  
The TreeNode class is already defined for you:


javaclass TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}


static void inorder(TreeNode node, ArrayList<Integer> list){
        if(node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    static ArrayList<Integer> traversal(TreeNode node){

        ArrayList<Integer> result = new ArrayList<>();

        inorder(node, result);

        return result;
    }
