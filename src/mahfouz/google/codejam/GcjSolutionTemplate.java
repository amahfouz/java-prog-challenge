package mahfouz.google.codejam;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Template for GCJ solutions.
 */
public final class GcjSolutionTemplate {

    private void solveCase(int caseNum, PrintStream out) {

    }

    private GcjSolutionTemplate(Scanner s) {

    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            new GcjSolutionTemplate(s).solveCase(i + 1, out);
            System.out.println("Finished " + (i + 1));
        }

        s.close();
        out.close();
    }

    private static void outputCase(PrintStream out, int caseNum, String output) {
        out.println("Case #" + caseNum + ": " + output);
    }

    private static void outputCase(PrintStream out, int caseNum, long output) {
        out.println("Case #" + caseNum + ": " + output);
    }

}
