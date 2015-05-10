package mahfouz.google.codejam.y2014.round1b;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem b.
 */
public final class ProblemBSmall {

    private final long a;
    private final long b;
    private final long k;

    private void solveCase(int caseNum, PrintStream out) {

        long[] x = new long[] {a , b, k};
        Arrays.sort(x);

        int pairs = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if ((i & j) < k) {
                    pairs++;
                }
            }
        }
        outputCase(out, caseNum, pairs);
    }

    private ProblemBSmall(Scanner s) {
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
            new ProblemBSmall(s).solveCase(i + 1, out);
            System.out.println("Finished " + (i + 1));
        }

        s.close();
        out.close();
    }

    private static void outputCase(PrintStream out, int caseNum, long output) {
        out.println("Case #" + caseNum + ": " + output);
    }

}
