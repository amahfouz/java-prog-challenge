package mahfouz.hackerrank;

import java.util.Arrays;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/value-of-friendship/problem
 */
public final class ValueOfFriendship {

    static long valueOfFriendsship(int numNodes, int[][] friendships) {

        Edge[] edges = new Edge[friendships.length];
        for (int i = 0; i < edges.length; i++) {
            // convert the nodes to 0 based
            edges[i] = new Edge(friendships[i][0], friendships[i][1]);
        }
        
        Subset[] sets = new Subset[numNodes];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new Subset();
            sets[i].parent = i;
            sets[i].rank = 0;
            sets[i].redundantEdges = 0;
            sets[i].numMembers = 1;
        }
        
        for (Edge e : edges) {
            if (edgeFormsCycle(e, sets))
                // edge forms a cycle, set it aside
                sets[findParent(e.from, sets)].redundantEdges++;
            else
                // add edge as it does not form a cycle
                addEdge(sets, e);
        }
        
        // sort the sets according to size
        
        Arrays.sort(sets);
        
        long totalValue = 0;
        for (Subset set : sets) {
            // TODO
            // do the computation
        }
        return totalValue;
    }
    
    private static void addEdge(Subset[] sets, Edge e) {
        Subset set1 = sets[e.from];
        Subset set2 = sets[e.to];
        
        if (set1.rank < set2.rank) {
            // append set2 to set1
            set2.parent = findParent(set1.parent, sets);
            set1.numMembers += set2.numMembers;  
        }
        else if (set2.rank < set1.rank) {
            // append set1 to set2
            set1.parent = findParent(set2.parent, sets);
            set2.numMembers += set1.numMembers;
        }
        else {
            // same rank - pick one to append to the other and increase rank
            set1.parent = findParent(set2.parent, sets);
            set2.numMembers += set1.numMembers;
            set1.rank++;
        }
    }

    private static boolean edgeFormsCycle(Edge e, Subset[] sets) {
        return (findParent(e.from, sets) == findParent(e.to, sets));
    }
    
    private static int findParent(int node, Subset[] sets) { 
        if (sets[node].parent != node)
            sets[node].parent = findParent(sets[node].parent, sets);
        
        return sets[node].parent; 
    }
    
    
    //
    // Nested
    //
    
    private static final class Subset implements Comparable<Subset>{
        
        private int parent;
        private int rank;
        private int numMembers;
        private int redundantEdges; 
        
        public String toString() {
            return "parent=" + parent 
                + ", numMembers=" + numMembers 
                + ", rank=" + rank;
        }
        
        public int compareTo(Subset other) {
            return this.numMembers - other.numMembers;
        }
    }
    
    private static final class Edge {

        private final int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
