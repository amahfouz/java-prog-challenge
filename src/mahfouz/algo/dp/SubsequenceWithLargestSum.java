package mahfouz.algo.dp;

/**
 * Find the sequence with the largest sum.
 */
public final class SubsequenceWithLargestSum {

    public static StartEnd computeLinear(int [] array) {

        int bestStart = 0;
        int bestEnd = 0;
        int bestSum = 0;

        int head = 0;
        int curSum = 0;

        while (head < array.length) {

            curSum += array[head];
            if (curSum > bestSum) {
                bestSum = curSum;
                bestEnd = head;
            }
            else if (curSum < 0) {
                bestStart = head + 1;
                bestEnd = bestStart;
                curSum = 0;
                //wrong();
                // TODO
                bestSum = 0;
            }
            head++;
        }

        if (bestStart > array.length)
            System.err.println("negative");
        else
            System.err.println(bestStart + ", " + bestEnd + " = " + bestSum);

        return null;
    }

    public static void main(String[] args) {
        computeLinear(new int[] {-1, 3, 1, -2, -3, 3, -3, 2, 2});
    }

    private static final class StartEnd {

        private final int start;
        private final int end;

        public StartEnd(int start, int end) {
            assert(end >= start);
            this.start = start;
            this.end = end;
        }
    }
}
