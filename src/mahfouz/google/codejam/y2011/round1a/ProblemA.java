package mahfouz.google.codejam.y2011.round1a;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for Problem A (Round 1B 2011)
 */
public final class ProblemA {

    private void solveCase(int caseNum, PrintStream out) {

    }

    private ProblemA(Scanner s) {

    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new ProblemA(s).solveCase(i + 1, out);
            System.out.println("Finished " + (i + 1));
        }

        s.close();
        out.close();
    }

    private void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

    private void outputCase(PrintStream out, int caseNum, long output) {
        out.println("Case #" + caseNum + ": " + output);
    }

}
