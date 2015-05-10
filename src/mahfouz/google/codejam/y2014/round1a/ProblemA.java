package mahfouz.google.codejam.y2014.round1a;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Solution for problem A in round 1A.
 */
public final class ProblemA {

    private final int len;
    private final long[] initial;
    private final long[] desired;

    public static void main(String[] args) throws Exception{

        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ProblemA(s).solveCase(i + 1, s, out);
        }
    }

    private ProblemA(Scanner s) {
        int n = s.nextInt();

        this.len = s.nextInt();

        this.initial = new long[n];
        for (int i = 0; i < initial.length; i++)
            initial[i] = Long.parseLong(s.next(), 2);

        this.desired = new long[n];
        for (int i = 0; i < desired.length; i++)
            desired[i] = Long.parseLong(s.next(), 2);
    }

    private void solveCase(int caseNum, Scanner s, PrintStream out) {

        if (initial.length == 1) {
            int hammingForOne = countOneBits(initial[0] ^ desired[0]);
            outputCase(out, caseNum, "" + hammingForOne);
            return;
        }

        // hammings[i][j] = hamming distance between desired[i] and initial[j]
        int[][] hammings = new int[desired.length][initial.length];

        Set masterSet = new HashSet();
        for (int i = 0; i < desired.length; i++) {
            Set hammingForDesired = new HashSet();
            for (int j = 0; j < initial.length; j++) {
                int hamming = countOneBits(desired[i] ^ initial[j]);
                hammingForDesired.add(hamming);

                hammings[i][j] = hamming;
            }

            if (i == 0)
                masterSet.addAll(hammingForDesired);
            else
                masterSet.retainAll(hammingForDesired);
        }

        List sorted = new ArrayList();
        sorted.addAll(masterSet);
        Collections.sort(sorted);

        for (int i = 0; i < sorted.size(); i++) {
            Integer hamming = (Integer)sorted.get(i);
            if (assignRow(hammings, hamming, 0, new HashSet(), -1)) {
                outputCase(out, caseNum, hamming.toString());
                return;
            }
        }

        outputCase(out, caseNum, "NOT POSSIBLE");
    }

    private final boolean assignRow
        (int[][] hammings, int hamming, int row, Set colsUsed, long mask) {

        if (row == hammings.length)
            return true;

        for (int col = 0; col < hammings[row].length; col++) {
            if (hammings[row][col] == hamming && (! colsUsed.contains(col))) {

                if (row != 0) {
                    if ((mask ^ desired[row]) != initial[col])
                        continue;
                }
                else // establish the mask based on first row
                    mask = desired[row] ^ initial[col];

                colsUsed.add(col);

                if (assignRow(hammings, hamming, row+1, colsUsed, mask))
                    return true;

                // failed - revert
                colsUsed.remove(col);
            }
        }
        return false;
    }

    private int countOneBits(long num) {
        return countOneBits(num, len);
    }

    private static int countOneBits(long num, int len) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            long mask = 1L << i;
            if ((num & mask) != 0)
                count++;
        }
        return count;
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
