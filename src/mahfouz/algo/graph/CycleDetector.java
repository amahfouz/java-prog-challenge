package mahfouz.algo.graph;

import java.util.List;

/**
 * Detects cycles in a directed graph.
 */
public final class CycleDetector {
    
    private final List<List<Integer>> adjacency;
    private final boolean[] visited;
    private final boolean[] onStack;
    
    // Adjacency list contains null entries for neighbor-less ndoes
    public static boolean graphHasCycles(List<List<Integer>> adjacency) {
        CycleDetector detector = new CycleDetector(adjacency);
        return detector.detectCycles();
    }
    
    private CycleDetector(List<List<Integer>> adjacency) {
        int numNodes = adjacency.size();
        this.visited = new boolean[numNodes];
        this.onStack = new boolean[numNodes];
        this.adjacency = adjacency;
    }

    private boolean detectCycles() {
        for (int i = 0; i < adjacency.size(); i++) {
            if (visited[i])
                continue;
            
            if (detectCycleStarting(i))
                return true;
        }
        return false;
    }
    
    private boolean detectCycleStarting(int node) {
        if (onStack[node])
            return true;
        
        if (visited[node])
            return false;

        onStack[node] = true;
        visited[node] = true;
        
        List<Integer> neighbors = adjacency.get(node);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (detectCycleStarting(neighbor))
                    return true;
            }
        }
        
        onStack[node] = false;
        
        return false;
    }
}