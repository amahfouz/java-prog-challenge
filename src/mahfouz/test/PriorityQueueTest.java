package mahfouz.test;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class PriorityQueueTest {

	private static final int[] dist = new int[] { 2, 1, 3, 0 };

	public static void main(String[] args) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(100, new MinDist());
		for (int i = 0; i < dist.length; i++) {
			q.add(i);
		}
		
		while (! q.isEmpty()) {
			System.out.println(q.poll());
			dist[1] += 2;
		}
 	}
	
	private static final class MinDist implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return dist[o1] - dist[o2];
		}
	}
}
