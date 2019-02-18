package mahfouz.geeks4geeks;

/**
 * Solution for 
 * https://www.geeksforgeeks.org/find-edge-disjoint-paths-two-vertices/
 */
public final class EdgeDisjointPaths {

    // Perform a DFS on the graph starting from the source
    // For every edge, do not traverse it if it is already visited
    // If the destination node is reached return true from 
    // the DFS method, all the way up the stack marking all
    // the edges in the path as visited.

    private final int[][] graph;
    private final int target;
    
    private int numPaths = 0;
    
    public EdgeDisjointPaths(int[][] graph, int src, int dst) {
        this.graph = graph;
        this.target = dst;
        
        int[] adjacents = graph[src];
        for (int adj = 0; adj < adjacents.length; adj++) {
            if (adjacents[adj] > 0)
                if (dfs(adj))
                    numPaths++;
        }
    }

    private boolean dfs(int node) {
        
        int[] adjacents = graph[node];
        for (int adj = 0; adj < adjacents.length; adj++) {
            int edge = adjacents[adj];
            if (edge <= 0)
                continue;

            if (adj == target) 
                return true;
            
            // mark edge as visited before traversing to avoid cycles
            adjacents[adj] = -1;
            
            if (dfs(adj))
                return true;;
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] graph = { 
                {0, 1, 1, 1, 0, 0, 0, 0}, 
                {0, 0, 1, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 1, 0, 0, 1, 0}, 
                {0, 0, 0, 0, 0, 0, 1, 0}, 
                {0, 0, 1, 0, 0, 0, 0, 1}, 
                {0, 1, 0, 0, 0, 0, 0, 1}, 
                {0, 0, 0, 0, 0, 1, 0, 1}, 
                {0, 0, 0, 0, 0, 0, 0, 0} 
              }; 
        
        EdgeDisjointPaths solution = new EdgeDisjointPaths(graph, 0, 7);
        
        System.out.println(solution.numPaths);
    }
}
