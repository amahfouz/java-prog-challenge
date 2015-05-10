package mahfouz.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class SnakesAndLaddersTheQuickestWayUp {

	private static final int NUM_SQUARES = 100;
	private static final int INFINITY = 100000;

	@SuppressWarnings("rawtypes")
	private SnakesAndLaddersTheQuickestWayUp(Scanner s, int caseNum) {
		
		int nLadders = s.nextInt();
		int nSnakes = s.nextInt();
		
		// read snakes and ladders
		
		HashMap<Integer,Integer> jumps = new HashMap<Integer,Integer>();
		
		populateMap(nLadders, jumps, s);
		populateMap(nSnakes, jumps, s);

		// construct graph
		
		// Adjacency list where distance between each pair of nodes is 1 dice throw
		ArrayList<Integer>[] adjacency = new ArrayList[NUM_SQUARES + 1];
		for (int cell = 1; cell <= NUM_SQUARES; cell++) 
			adjacency[cell] = new ArrayList();
		
		for (int cell = 1; cell <= NUM_SQUARES; cell++) {
			for (int dice = 1; dice <= 6; dice++) {
				int landing = cell + dice;
				if (landing > NUM_SQUARES)
					continue;
				
				if (jumps.containsKey(landing)) 
					adjacency[cell].add(jumps.get(landing));
				else
					adjacency[cell].add(landing);
			}
		}

		// maintain reference to nodes
		
		Node[] nodes = new Node[NUM_SQUARES + 1];
		nodes[1] = new Node(1, 0);
		for (int cell = 2; cell <= NUM_SQUARES; cell++) {
			nodes[cell] = new Node(cell, INFINITY);
		}
		
		// head of queue is node 0
		Node head = nodes[1];
		Node tail = nodes[2];
		
		head.next = tail;
		tail.prev = head;
		
		// insert all nodes at tail of queue
		for (int i = 3; i <= NUM_SQUARES; i++) {
			Node node = nodes[i];
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		
		// compute shortest path

		while (head != null) {
			Node minDistNode = head;
			
			// square 100 has been computed - stop now
			if (minDistNode.cell == NUM_SQUARES) 
				break;
			
			ArrayList<Integer> adjacentForNode = adjacency[minDistNode.cell];
			
			// remove node at head
			if (head.next != null)
				head.next.prev = null;
			head = head.next;
			
			for (int adj : adjacentForNode) {
				int altDist = minDistNode.dist + 1; // one dice roll
				Node adjNode = nodes[adj];
				if (altDist < adjNode.dist) {
					adjNode.setDist(altDist);
				
					// re sort nodes
					
					while ((adjNode.prev != null) && (adjNode.compareTo(adjNode.prev) < 0)) {
						Node tmp = adjNode.prev;
						adjNode.prev = adjNode.prev.prev;
						if (adjNode.prev != null)
							adjNode.prev.next = adjNode;
						if (adjNode.next != null)
							adjNode.next.prev = tmp;
						tmp.next = adjNode.next;
						tmp.prev = adjNode;
						adjNode.next = tmp;
					}
					
					if (adjNode.prev == null)
						head = adjNode;
				}
			}
		}
		System.out.println(nodes[NUM_SQUARES].dist);
	}
	
	private void populateMap(int num, HashMap<Integer,Integer> map, Scanner s) {
		for (int i = 0; i < num; i++) {
			int from = s.nextInt();
			int to = s.nextInt();
			map.put(from, to);
		}
	}
	
	private static final class Node implements Comparable {
		
		private final int cell;
		private int dist;
		private Node next;
		private Node prev;
		
		public Node(int cellLabel, int dist) {
			this.cell = cellLabel;
			this.dist = dist;
		}

		public void setDist(int newDist) {
			this.dist = newDist;
		}
		
		@Override
		public int compareTo(Object object) {
			if (! (object instanceof Node))
				return 1;
			
			Node other = (Node)object;
			
			int diffDist = dist - other.dist;
			return (diffDist != 0)
				? diffDist
			    : (dist == INFINITY)
			    	? cell - other.cell      // compute lower nodes first  
			    	: - (cell - other.cell); // higher cells are closer to goal
		}
		
		public String toString() {
			return "(" + cell + ": " + dist + ")";
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		s.useDelimiter("\\p{javaWhitespace}+|,");

		int numCases = s.nextInt();
		for (int i = 0; i < numCases; i++) {
			new SnakesAndLaddersTheQuickestWayUp(s, i);
		}
	}
}
