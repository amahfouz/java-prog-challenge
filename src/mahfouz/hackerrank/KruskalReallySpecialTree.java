package mahfouz.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/kruskalmstrsub/problem
 */
public final class KruskalReallySpecialTree {

    
    public static int kruskals(int numNodes, 
                               List<Integer> from, 
                               List<Integer> to, 
                               List<Integer> weights) {

        Edge[] edges = new Edge[weights.size()];
        for (int i = 0; i < edges.length; i++) {
            // convert the nodes to 0 based
            edges[i] = new Edge(from.get(i) - 1, to.get(i) - 1, weights.get(i));
        }
        
        Arrays.sort(edges);
        
        Subset[] sets = new Subset[numNodes];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Subset();
            sets[i].parent = i;
            sets[i].rank = 0;
        }
        
        int totalValue = 0;
        for (Edge e : edges) {
            if (edgeFormsCycle(e, sets)) 
                continue;
            
            // add edge as it does not form a cycle
            addEdge(sets, e);
            totalValue += e.weight;
        }
        return totalValue;
    }
    
    private static void addEdge(Subset[] sets, Edge e) {
        int root1 = findParent(e.from, sets);
        int root2 = findParent(e.to, sets);
        Subset set1 = sets[root1];
        Subset set2 = sets[root2];

        
        if (set1.rank < set2.rank) {
            // append set2 to set1
            set2.parent = root1;
        }
        else if (set2.rank < set1.rank) {
            // append set1 to set2
            set1.parent = root2;
        }
        else {
            // same rank - pick one to append to the other and increase rank
            set1.parent = root2;
            set1.rank++;
        }
    }

    private static boolean edgeFormsCycle(Edge e, Subset[] sets) {
        int p1 = findParent(e.from, sets);
        int p2 = findParent(e.to, sets);
        return (p1 == p2);
    }
    
    private static int findParent(int node, Subset[] sets) { 
        if (sets[node].parent != node)
            sets[node].parent = findParent(sets[node].parent, sets);
        
        return sets[node].parent; 
    }
    
    
    //
    // Nested
    //
    
    private static final class Subset {
        
        private int parent;
        private int rank;
        
        public String toString() {
            return "parent=" + parent 
                + ", rank=" + rank;
        }
    }
    
    private static final class Edge implements Comparable<Edge> {

        private final int from, to;
        private final int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    
    // main copied as is from the problem
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //int res = Result.;

        System.out.println(kruskals(gNodes, gFrom, gTo, gWeight));
        // Write your code here.

        bufferedReader.close();
    } 
        
}
