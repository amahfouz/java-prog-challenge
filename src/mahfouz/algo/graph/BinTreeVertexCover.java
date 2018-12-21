package mahfouz.algo.graph;

/**
 * Solution of vertex cover for binary tree.
 * https://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming-solution-tree/
 */
public final class BinTreeVertexCover {

    public static int solveRecursive(Node root) {
        if (root == null)
            return 0;
        
        int numIfRootIncluded 
            = 1 
            + solveRecursive(root.left)
            + solveRecursive(root.right);
        
        int numIfRootExcluded = 0;
        if (root.left != null)
            numIfRootExcluded 
                += 1
                + solveRecursive(root.left.left)
                + solveRecursive(root.left.right);
        
        if (root.right != null)
            numIfRootExcluded 
                += 1 
                + solveRecursive(root.right.left)
                + solveRecursive(root.right.right);
        
        return Math.min(numIfRootIncluded, numIfRootExcluded);
    }
    
    private static final class Node {
        
        private final Node left, right;

        public Node() {
            this.left = null;
            this.right = null;
        }
        
        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) {
        Node root = new Node
           (new Node(null, 
                     new Node()),
            new Node(new Node(),   
                     new Node(new Node(),
                              new Node())));
        
        System.out.println(solveRecursive(root));
    }
}
