package mahfouz.google.codejam.y2014.round1a;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for problem C in round 1A.
 */
public final class ProblemC {

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(i + 1, s, out);
        }
    }

    private static void solveCase(int caseNum, Scanner s, PrintStream out) {
        int count = s.nextInt();
        int num;
        for (int i = 0; i < count; i++) {
            num = s.nextInt();
            if (num == i) {
                outputCase(out, caseNum, "BAD");
                return;
            }
        }
        outputCase(out, caseNum, "GOOD");
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }
}
