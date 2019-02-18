package mahfouz.geeks4geeks;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Solution for 
 * https://practice.geeksforgeeks.org/problems/possible-paths/0
 */
public final class PossiblePathsWithKEdges {
    
    // not explicitly given in the problem - arbitrarily chosen
    private static final int MAX_K = 100;
    
    // adjacency matrix
    private final boolean [][] graph;
    // computed num paths from each vertex for each nHop <= k
    private final int [][] result;
    private final int u, v, k;
    
    public PossiblePathsWithKEdges(BufferedReader r) throws Exception {
        int vertices = Integer.parseInt(r.readLine()); 
        this.graph = new boolean[vertices][vertices];
        this.result = new int[vertices][MAX_K];
        
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = -1;
            }
        }
        
        String line = r.readLine();
        String[] matrix = line.trim().split("\\s+");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = (Integer.parseInt(matrix[vertices * i + j]) == 1);
            }
        }
        String[] uvk = r.readLine().trim().split("\\s+");
        this.u = Integer.parseInt(uvk[0]);
        this.v = Integer.parseInt(uvk[1]);
        this.k = Integer.parseInt(uvk[2]);
    }
    
    public int solve() {
        return dfs(u, k);
    }
    
    private int dfs(int src, int hops) {
        if (hops == 0) {
            if (src == v)
                return 1;
            return 0; // either way return - no more hops
        }
        
        if (result[src][hops] != -1)
            return result[src][hops];
        
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[src][i]) {
                count += dfs(i, hops - 1);
            }
        }
        result[src][hops] = count;
        
        return count;
    }
    
    //
    // Main
    //

    public static void main(String[] args) throws Exception {
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in)); 

        int numCases = Integer.parseInt(br.readLine()); 
        
        for (int i = 0; i < numCases; i++) {
            PossiblePathsWithKEdges problem 
                = new PossiblePathsWithKEdges(br);
            System.out.println(problem.solve());
        }
    }
}
