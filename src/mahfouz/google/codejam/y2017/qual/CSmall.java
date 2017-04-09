package mahfouz.google.codejam.y2017.qual;

import java.util.PriorityQueue;
import java.util.Scanner;

public final class CSmall {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        s.nextLine();

        for (int i = 0; i < numCases; i++) {
            solveCase(i+1, s);
        }
    }

    private static void solveCase(int caseNum, Scanner s) {
        int n = s.nextInt();
        int k = s.nextInt();

        PriorityQueue<Segment> segments = new PriorityQueue<Segment>();
        segments.add(new Segment(0, n-1));

        // occupy k - 1 stalls
        for (int i = 0; i < k - 1; i++) {
            Segment seg = segments.poll();
            if (seg.size() == 1)
                break;

            int stallPos = seg.pickStall();
            if (seg.left < stallPos)
                segments.add(new Segment(seg.left, stallPos - 1));
            if (stallPos < seg.right)
                segments.add(new Segment(stallPos + 1, seg.right));
        }

        // get the kth segment
        Segment largest = segments.poll();
        int pos = largest.pickStall();
        int ls = pos - largest.left;
        int rs = largest.right - pos;

        int max = Math.max(ls, rs);
        int min = Math.min(ls, rs);

        System.out.println("Case #" + caseNum + ": " + max+ " " + min);
    }

    private static final class Segment implements Comparable<Segment> {

        private final int left;
        private final int right;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int size() {
            return right - left + 1;
        }

        public int pickStall() {
            int size = size();
            int pos = (size % 2 == 0)
                ? (size / 2) - 1
                : size / 2;
            return left + pos;
        }

        @Override
        public int compareTo(Segment other) {
            int sizeDiff = other.size() - size();
            return sizeDiff != 0
                ? sizeDiff
                : left - other.left;
        }

        public String toString() {
            return "[" + left + ", " + right + "]";
        }
    }
}
