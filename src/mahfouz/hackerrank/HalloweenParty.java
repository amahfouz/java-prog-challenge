package mahfouz.hackerrank;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solution for the "Halloween Party" problem.
 */
public final class HalloweenParty {

    private static void solveCase(Scanner s, int caseNum, PrintStream out) {
        int numCuts = s.nextInt();

        int div2 = numCuts / 2;
        int mod2 = numCuts % 2;

        int result = (mod2 == 0)
            ? div2 * div2
            : div2 * (div2 + 1);

        out.println(result);
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner
            (new File("C:\\Users\\amahfouz\\Downloads\\cj.in"));
        PrintStream out = new PrintStream
            (new File("C:\\Users\\amahfouz\\Desktop\\solution.txt"));

        int numCases = s.nextInt();

        for (int i = 0; i < numCases; i++) {
            solveCase(s, i + 1, out);
        }

        s.close();
        out.close();
    }
}
