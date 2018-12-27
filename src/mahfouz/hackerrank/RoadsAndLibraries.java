package mahfouz.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/torque-and-development/problem
 */
public final class RoadsAndLibraries {

    static long roadsAndLibraries
        (int n, int costLib, int costRoad, int[][] roads) {

        for (int i = 0; i < roads.length; i++) {
            for (int j = 0; j < roads[i].length; j++) {
                roads[i][j]--;
            }
        }
        
        // build a lib in each city
        if (costRoad > costLib) 
            return n * (long)costLib;

        long totalCost = 0;
        
        // otherwise build a lib in each component and connect its nodes
        Subset[] components = divideIntoSubsets(roads, n);
        int numComponents = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i].parent == i) {
                numComponents++;
                // rank - 1 edges to connect cities in a component
                totalCost += costRoad * (components[i].numMembers - 1); 
            }
        }
        // one lib per connected component
        totalCost += numComponents * costLib;
        
        return totalCost;
    }

    private static Subset[] divideIntoSubsets(int roads[][], int n) {
        // disjoint sets of vertices
        // ith item stores "parent" of ith city, -1 if no parent
        Subset[] cities = new Subset[n];

        // init all as disconnected
        for (int i = 0; i < cities.length; i++) {
            Subset subset = new Subset();
            subset.parent = i;
            subset.rank = 0;
            subset.numMembers = 1;
            
            cities[i] = subset;
        }

        // go over all the edges
        for (int i = 0; i < roads.length; i++) {
            int c1 = roads[i][0];
            int c2 = roads[i][1];
            
            // if both are connected, nothing to do 
            if (findParent(cities, c1) == findParent(cities, c2))
                continue;
            
            union(c1, c2, cities);
        }
        
        return cities;
    }
    
    private static void union(int city1, int city2, Subset[] cities) {
        int root1 = findParent(cities, city1);
        int root2 = findParent(cities, city2);
        Subset sub1 = cities[root1];
        Subset sub2 = cities[root2];
        
        if (sub1.rank < sub2.rank) {
            sub2.parent = root1;
            sub1.numMembers += sub2.numMembers;
        }
        else if (sub2.rank < sub1.rank) {
            sub1.parent = root2;
            sub2.numMembers += sub1.numMembers;
        }
        else {
            // equal ranks, just attach any of them to the other
            sub1.parent = root2;
            sub2.numMembers += sub1.numMembers;
            sub2.rank++;
        }
    }
    
    // fins parent and compresses the path
    private static int findParent(Subset[] cities, int city) {
        if (cities[city].parent != city)
            cities[city].parent = findParent(cities, cities[city].parent);
        
        return cities[city].parent;
    }

    
    private static final class Subset {
        private int parent;
        private int rank;
        private int numMembers;
        
        public String toString() {
            return "parent=" + parent + ", numMembers=" + numMembers + ", rank=" + rank;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            System.out.println(String.valueOf(result));
        }


        scanner.close();
    }
}
