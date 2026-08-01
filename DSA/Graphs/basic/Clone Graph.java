Problem: Given a reference to a node in a connected undirected graph, return a deep copy of the graph. Each node contains a value and a list of its neighbors:


javaclass Node {
    public int val;
    public List<Node> neighbors;
    
    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

Graph:      1 —— 2
            |    |
            4 —— 3

Input: node 1 (with neighbors [2,4])
Output: a completely NEW graph with the same structure — new Node objects, same values and connections



  /*
 * Problem: Clone Graph
 * Pattern: Graph DFS + HashMap (original → clone)
 * Time: O(V + E), Space: O(V)
 * Key insight: HashMap<original, clone> is both the visited-set AND the lookup for wiring edges
 * Key insight: map.put BEFORE recursing on neighbors — else mutual edges recurse forever
 * Key insight: c.neighbors.add(cloneGraph(neighbor)) — add the CLONE, never the original
 * Gotcha: HashSet insufficient — need to retrieve the copy, not just know "visited"
 */


static HashMap<Node, Node> map = new HashMap<>();

static Node cloneGraph(Node node){

if(node == null) return null;

if(map.containsKey(node)) return map.get(node);
Node c = new Node(node.val);

map.put(node, c);

for(Node neighbor : node.neighbors) {
c.neighbors.add(cloneGraph(neighbor));
}

return c;

}
