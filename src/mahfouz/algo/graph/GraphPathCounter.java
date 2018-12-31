package mahfouz.algo.graph;

import java.util.List;

/**
 * Counts paths from a graph node to another.
 */
public final class GraphPathCounter {

    private final List<List<Integer>> adjacency;
    private final int targetNode;
    private int numPaths = 0;
    
    // counts paths from first node (0) to last node (n - 1)
    // graph is assumed to contain no cycles
    public static int countPaths(List<List<Integer>> adjacency) {
        GraphPathCounter counter = new GraphPathCounter(adjacency);
        counter.countPaths(0);
        return counter.numPaths;
    }

    // Adjacency list contains null
    private GraphPathCounter(List<List<Integer>> adjacency) {
        this.adjacency = adjacency;
        this.targetNode = adjacency.size() - 1;
    }

    private void countPaths(int node) {
        List<Integer> neighbors = adjacency.get(node);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (neighbor == targetNode)
                    numPaths++;
            }
        }
    }
}
