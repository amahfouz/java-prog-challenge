package mahfouz.hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

public final class EvenTree {

	private static ArrayList<Integer>[] adjacency;
	private static boolean[] visited;
	private static int numCuts = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int v = s.nextInt();
		int e = s.nextInt();
		
		visited = new boolean[v];
		adjacency = new ArrayList[v];
		
		for (int i = 0; i < v; i++) 
			adjacency[i] = new ArrayList<Integer>();
		
		
		for (int i = 0; i < e; i++) {
			int n1 = s.nextInt() - 1;
			int n2 = s.nextInt() - 1;
			
			adjacency[n1].add(n2);
			adjacency[n2].add(n1);
		}
		
		considerCutAt(0);
		
		System.out.println(numCuts);
	}
	
	private static int considerCutAt(int node) {
		
		visited[node] = true;
		
		int numNodesInMySubTree = 1; // include self
		
		ArrayList<Integer> neighbors = adjacency[node];
		for (int neighbor : neighbors) {
			if (visited[neighbor])
				continue;
			
			int numNodesInChildSubTree = considerCutAt(neighbor); 

			// cut child if possible
			if (numNodesInChildSubTree % 2 == 0)
				numCuts++;

			numNodesInMySubTree += numNodesInChildSubTree;
		}
		
		return numNodesInMySubTree;
	}
}
