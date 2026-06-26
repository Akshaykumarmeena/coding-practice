

Example:
        3
       / \
      9  20
         / \
        15   7

Output: [[3], [9, 20], [15, 7]]



/*
 * Problem: Binary Tree Level Order Traversal
 * Pattern: BFS using Queue
 * Time: O(n), Space: O(n)
 * Key insight: queue.size() at start of each iteration = exact number of nodes at current level
 * Gotcha: add TreeNode to queue, not node.val
 * Gotcha: use offer()/add() to enqueue, poll() to dequeue
 * Java: Queue<TreeNode> queue = new LinkedList<>()
 */


static ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode root){
		
		if(root == null) return null;

		Queue<TreeNode> queue = new LinkedList<>();

		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	
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

			result.add(temp);			

		}
		
	return result;
}
