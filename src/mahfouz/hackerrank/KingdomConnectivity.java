package mahfouz.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/kingdom-connectivity/problem
 * 
 * State 
 *   for every node:
 *   - Number of paths found from the node till target so far
 *   for traversal
 *   - Nodes on current path
 *   - Nodes that have been found to be in a cycle
 *   
 * Traverse starting source node
 *  - Add node to current path
 *  - Check if cycle is formed --> traverse back and add all to cycle
 *  - Otherwise, visit all children
 *  -   For any child whose number of paths has been computed, add to all
 *      nodes on the current path.
 * 
 */
public final class KingdomConnectivity {    
    
    private static final int TEN_POW_NINE = 1000000000;
    
    private final List<Integer>[] adjacency;
    // Flags whether the corresponding node is in a cycle
    private final boolean[] inCycle;
    // Number of paths found from the node till target so far
    private final long[] numPathsFromHere;
    // Nodes in the current path (nodes on the stack)
    private final List<Integer> curPath; 
    
    public KingdomConnectivity(List<Integer>[] adjacency) {
        int numNodes = adjacency.length;
        this.adjacency = adjacency;
        this.inCycle = new boolean[numNodes];
        this.numPathsFromHere = new long[numNodes];
        this.curPath = new ArrayList<Integer>();
    }
    
    public void countPaths() {
        visit(0);
        if (intersectCyclesAndPaths())
            System.out.println("INFINITE PATHS");
        else
            System.out.println(numPathsFromHere[0]);
    }
    
    public void visit(int node) {
        boolean addedToPath = false;
        if (curPath.contains(node)) {
            addToCycle(node);
        }
        else {
            curPath.add(node);
            addedToPath = true;
            // check for target node
            if (node == adjacency.length - 1)
                incrementNumPaths(1);
            
            List<Integer> neighbors = adjacency[node];
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    long pathsFromNeighbor = numPathsFromHere[neighbor];
                    
                    // if it was determined that no paths exist from 
                    // here onwards then do not recurse
                    if (pathsFromNeighbor == -1)
                        continue;
                    
                    if (pathsFromNeighbor != 0)
                        incrementNumPaths(pathsFromNeighbor);
                    else
                        visit(neighbor);
                }
            }
        }
        // no paths found - mark as visited
        if (numPathsFromHere[node] == 0)
            numPathsFromHere[node] = -1;
        
        // remove current node from path - pop off the stack
        if (addedToPath)
            curPath.remove(curPath.size() - 1);
    }
    
    // determine if any of the nodes on any of the paths is
    // also part of any of the cycles
    private boolean intersectCyclesAndPaths() {
        for (int i = 0; i < inCycle.length; i++) {
            if (inCycle[i] && numPathsFromHere[i] > 0)
                return true;
        }
        return false;
    }
    
    // adds the specified number to the number of paths from
    // each of the nodes on the current path
    private void incrementNumPaths(long numToAdd) {
        for (Integer pathNode : curPath) {
            numPathsFromHere[pathNode] += numToAdd;
            numPathsFromHere[pathNode] %= TEN_POW_NINE;
        }
    }
    
    // Adds all nodes on current path to the set of cycle-forming nodes
    private void addToCycle(int endNode) {
        int pathNode;
        for (int i = curPath.size() - 1; i >=0; i--) {
            pathNode = curPath.get(i);
            inCycle[pathNode] = true;
            if (pathNode == endNode)
                break;
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int nodes = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        @SuppressWarnings("unchecked")
        List<Integer>[] adjacency = new List[nodes];

        IntStream.range(0, m).forEach(i -> {
            try {
                String[] edge = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                int from = Integer.parseInt(edge[0]) - 1;
                List<Integer> neighbors = adjacency[from];
                if (neighbors == null) {
                    neighbors = new ArrayList<Integer>();
                    adjacency[from] = neighbors;
                }
                int to = Integer.parseInt(edge[1]) - 1;
                neighbors.add(to);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        KingdomConnectivity kc = new KingdomConnectivity(adjacency);
        kc.countPaths();

        bufferedReader.close();
    }
    
}
