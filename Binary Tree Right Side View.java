Given the root of a binary tree, imagine yourself standing on the right side of it. Return the values of the nodes you can see, ordered from top to bottom.
          
  
  Example:
                  1
                 / \
                2   3
                 \   \
                  5   4
          
          Output: [1, 3, 4]

/*
 * Problem: Binary Tree Right Side View
 * Pattern: BFS using Queue
 * Time: O(n), Space: O(n)
 * Key insight: Level Order Traversal — just take last node of each level
 * Optimization: no need for temp list — track last polled node directly
 * Gotcha: return last element of each level, not first
 */

static ArrayList<Integer> rightSideView(TreeNode root){

if(root == null) return null;

Queue<TreeNode> queue = new LinkedList<>();

ArrayList<Integer> result = new ArrayList<>();

queue.add(root);

while(!queue.isEmpty()){

int n = queue.size();

ArrayList<Integer> temp = new ArrayList<>();

for(int i=0;i<n;i++){
TreeNode tmpNode = queue.poll();
temp.add(tmpNode.val);

if(tmpNode.left != null) queue.add(tmpNode.left);
if(tmpNode.right != null) queue.add(tmpNode.right);
}

result.add(temp.get(temp.size()-1));

}

// Optimised approach 
/*
TreeNode tmpNode = null;
for(int i=0; i<n; i++){
    tmpNode = queue.poll();
    if(tmpNode.left != null) queue.add(tmpNode.left);
    if(tmpNode.right != null) queue.add(tmpNode.right);
}
result.add(tmpNode.val);  // tmpNode is last node of level after loop
*/

return result;
}



