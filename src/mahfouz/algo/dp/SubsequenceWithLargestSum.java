package mahfouz.algo.dp;

/**
 * Find the sequence with the largest sum.
 */
public final class SubsequenceWithLargestSum {

    public static StartEnd computeLinear(int [] array) {

        // current best subsequence specified by those
        int bestStart = 0;
        int bestEnd = 0;
        int bestSum = 0;

        // current candidate subsequence specified by those
        int head = 0;
        int tail = -1;
        int curSum = 0;

        while (head < array.length) {

            curSum += array[head];
            if (curSum > bestSum) {
                bestSum = curSum;
                bestEnd = head;
                bestStart = tail + 1;
            }
            else if (curSum <= 0) {
                tail = head;
                curSum = 0;
            }
            head++;
        }

        if (bestStart > array.length)
            System.err.println("negative");
        else
            System.err.println(bestStart + ", " + bestEnd + " = " + bestSum);

        return new StartEnd(bestStart, bestEnd);
    }

    public static void main(String[] args) {
        //new int[] {-1, 3, 1, -2, -3, 3, -3, 2, 2}
        StartEnd result = computeLinear(new int[] {3, -2, -2});
        System.out.println(result);
    }

    private static final class StartEnd {

        private final int start;
        private final int end;

        public StartEnd(int start, int end) {
            assert(end >= start);
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "(" + start +", " + end +")";
        }
    }
}
