package mahfouz.google.codejam.y2014.round1b;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem b.
 */
public final class ProblemB {

    private final long a;
    private final long b;
    private final long k;

    private void solveCase(int caseNum, PrintStream out) {

        long[] x = new long[] {a , b, k};
        Arrays.sort(x);

        long m1 = x[0];
        long m2 = x[1];
        long m3 = x[2];

        int cases = 1;
        long pow2 = 1;
        while (m1 > pow2) {
            pow2 = pow2 << 1;
            cases *= 4;
        }

        pow2 -= 1;

        // reduce them all by pow2 - 1

        m1 -= pow2;
        m2 -= pow2;
        m3 -= pow2;

        if (m1 == 0 || m2 == 0) {
            outputCase(out, caseNum, cases);
            return;
        }

        int pairs = 0;
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                if ((m1 & m2) < m3) {
                    pairs++;
                }
            }
        }
        outputCase(out, caseNum, pairs + cases);
    }

    private ProblemB(Scanner s) {
        a = s.nextLong();
        b = s.nextLong();
        k = s.nextLong();
    }

    public static void main(String[] args) throws Exception{
//        int x = 1;
//        for (int i = 0; i < 9; i++) {
//            x *= 10;
//        }
//        System.out.println(Integer.MAX_VALUE - x);
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ProblemB(s).solveCase(i + 1, out);
        }

        s.close();
        out.close();
    }

    private static void outputCase(PrintStream out, int caseNum, long output) {
        System.out.println("Case #" + caseNum + ": " + output);
        out.println("Case #" + caseNum + ": " + output);
    }

}
